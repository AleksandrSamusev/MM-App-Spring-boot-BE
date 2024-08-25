package dev.practice.moneymanagementback.controllers;

import dev.practice.moneymanagementback.dtos.JWTAuthResponse;
import dev.practice.moneymanagementback.dtos.LoginDto;
import dev.practice.moneymanagementback.dtos.NewUserDto;
import dev.practice.moneymanagementback.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
@Validated
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody NewUserDto userNewDto) {
        String response = authService.register(userNewDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@Valid @RequestBody LoginDto loginDto) {
        JWTAuthResponse jwtAuthResponse = authService.login(loginDto);
        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
