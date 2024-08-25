package dev.practice.moneymanagementback.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String role;
    private String userId;
}
