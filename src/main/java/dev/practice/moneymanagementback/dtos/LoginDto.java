package dev.practice.moneymanagementback.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Login Dto",
        description = "model to hold new login request"
)
public class LoginDto {
    @Schema(
            description = "Username or email input",
            example = "user@user.user"
    )
    @NotEmpty(message = "Email field cannot be empty")
    private String usernameOrEmail;
    @Schema(
            description = "Password input"
    )
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
