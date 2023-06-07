package com.scut.backend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SaltPasswordUtil {
    private static  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public static String getToken(String pass) {
        return encoder.encode(pass);
    }
    public static boolean isMatch(String pass,String token) {
        return encoder.matches(pass,token);
    }
}
