package com.scut.backend.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scut.backend.bean.Chat;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MyWebSocketHandler implements WebSocketHandler {
    //静态变量，用来记录当前在线连接数。
    private static int onlineCount = 0;
    private static Map<String,WebSocketSession> webSocketMap = new ConcurrentHashMap<>();
    //接收sid
    private String name = "";

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 建立连接后逻辑
        this.name = extractIdFromPath(session.getUri().getPath());
        webSocketMap.put(name,session);
    }

    private String extractIdFromPath(String path) {
        System.out.println(path);
        String[] segments = path.split("/");
        if (segments.length > 0) {
            return segments[segments.length - 1];
        }
        return "";
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // 处理消息逻辑 没用
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 处理错误逻辑
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // 连接关闭逻辑
        System.out.println("关闭: "+this.name);
        webSocketMap.remove(name);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    public static void sendMsgIfOnline(String name, Chat chatInstance) throws IOException {
        System.out.println("sendMsgIfOnline: "+name);
        WebSocketSession session = webSocketMap.get(name);
        if(session == null) {
            return;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(chatInstance)));
    }
}
