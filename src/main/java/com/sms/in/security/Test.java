package com.sms.in.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Test {
    public static void main(String[] args) {
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        String encodedPassword=encoder.encode("admin");
        System.out.println(encodedPassword);
    }
}
