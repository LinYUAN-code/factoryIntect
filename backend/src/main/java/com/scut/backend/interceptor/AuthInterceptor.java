package com.scut.backend.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.backend.bean.User;
import com.scut.backend.mapper.UserMapper;
import com.scut.backend.utils.ResultUtils;
import com.scut.backend.utils.SaltPasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }
        System.out.println("check auto");
        String name = URLDecoder.decode((String) request.getHeader("name"),"UTF-8");;
        String token = request.getHeader("token");
        if (token == null || name == null) {
            // 如果请求头中没有token，返回未认证错误
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("name",name);
        User user = userMapper.selectOne(qw);

        if(user == null || !user.getToken().equals(token)) {
            return false;
        }
        return true;
    }
}
