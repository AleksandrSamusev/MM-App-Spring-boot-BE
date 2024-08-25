package dev.practice.moneymanagementback.services;

import dev.practice.moneymanagementback.dtos.JWTAuthResponse;
import dev.practice.moneymanagementback.dtos.LoginDto;
import dev.practice.moneymanagementback.dtos.NewUserDto;

public interface AuthService {

    String register(NewUserDto userNewDto);
    JWTAuthResponse login(LoginDto loginDto);
}
