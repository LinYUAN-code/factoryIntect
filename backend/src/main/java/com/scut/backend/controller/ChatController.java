package com.scut.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.backend.bean.Chat;
import com.scut.backend.mapper.ChatMapper;
import com.scut.backend.utils.MyWebSocketHandler;
import com.scut.backend.utils.Result;
import com.scut.backend.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("/chat")
@Controller
@CrossOrigin
public class ChatController {

    @Autowired
    ChatMapper chatMapper;

    @RequestMapping("/sendMsg")
    @ResponseBody
    public Result sendMsg(@RequestBody Map<String, Object> requestMap) throws IOException {
        String name = (String) requestMap.get("name");
        String toName = (String) requestMap.get("toName");
        String msg = (String) requestMap.get("msg");
        long time = System.currentTimeMillis();

        Chat chatInstance = Chat.builder().
                fromName(name).
                toName(toName).
                msg(msg).
                time(time).
                build();

        chatInstance.setChatId(chatMapper.insert(chatInstance));

        MyWebSocketHandler.sendMsgIfOnline(name,chatInstance);
        MyWebSocketHandler.sendMsgIfOnline(toName,chatInstance);

        return ResultUtils.success("发送消息成功");
    }


    @RequestMapping("/getMsgList")
    @ResponseBody
    public Result getMsgList(@RequestBody Map<String, Object> requestMap) {
        String name = (String) requestMap.get("name");
        String toName = (String) requestMap.get("toName");


        QueryWrapper<Chat> qw = new QueryWrapper<>();
        qw.and(q -> q.eq("fromName", name).eq("toName", toName))
                .or(q -> q.eq("fromName", toName).eq("toName", name));

        List<Chat> chats = chatMapper.selectList(qw);
        System.out.println(chats);

        return ResultUtils.success(chats);
    }
}
