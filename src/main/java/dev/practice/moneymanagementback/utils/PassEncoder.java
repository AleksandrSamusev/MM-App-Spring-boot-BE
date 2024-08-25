package dev.practice.moneymanagementback.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("user2"));
    }
}
