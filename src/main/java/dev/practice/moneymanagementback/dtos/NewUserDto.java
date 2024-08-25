package dev.practice.moneymanagementback.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "New user Dto",
        description = "The model to register new user"
)
public class NewUserDto {
    @Schema(
            description = "The first name of the user",
            example = "John"
    )
    @NotBlank(message = "User first name cannot be blank")
    @Length(max = 50, message = "Users first name should be 50 chars max")
    private String firstName;

    @Schema(
            description = "The last name of the user",
            example = "Doe"
    )
    @NotBlank(message = "User last name cannot be blank")
    @Length(max = 50, message = "Users last name should be 50 chars max")
    private String lastName;

    @Schema(
            description = "The username of the user",
            example = "JohnDoe"
    )
    @NotBlank(message = "Username cannot be blank")
    @Length(max = 50, message = "Username should be 50 chars max")
    private String username;

    @Schema(
            description = "The email address of the user",
            example = "john@doe.john"
    )
    @NotBlank(message = "User email cannot be blank")
    @Length(max = 50, message = "Email length should be 50 chars max")
    @Email(message = "Incorrect email format")
    private String email;

    @Schema(
            description = "Users password"
    )
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
