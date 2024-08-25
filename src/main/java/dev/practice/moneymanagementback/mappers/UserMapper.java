package dev.practice.moneymanagementback.mappers;

import dev.practice.moneymanagementback.dtos.NewUserDto;
import dev.practice.moneymanagementback.models.User;

public class UserMapper {
    public static User toUser(NewUserDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        return user;
    }
}
