package com.kevin.usc.core.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("kevin"));
        // $2a$10$yb4IMuD7pN22O5ggBPjS4edBIyJXLetVy96YL23TjwhjKHmtePhGu
    }
}
