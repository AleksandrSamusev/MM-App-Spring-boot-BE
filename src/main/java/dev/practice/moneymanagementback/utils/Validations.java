package dev.practice.moneymanagementback.utils;

import dev.practice.moneymanagementback.exception.ActionForbiddenException;
import dev.practice.moneymanagementback.exception.ResourceNotFoundException;
import dev.practice.moneymanagementback.models.Role;
import dev.practice.moneymanagementback.models.User;
import dev.practice.moneymanagementback.repositories.RoleRepository;
import dev.practice.moneymanagementback.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class Validations {
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final RoleRepository roleRepository;

    public User checkUserExist(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            log.error("User with id {} wasn't found", userId);
            throw new ResourceNotFoundException("User", "user id", userId.toString() );
        }
        return user.get();
    }

    public User checkUserExistsByUsernameOrEmail(String usernameOrEmail) {
        Optional<User> user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (user.isEmpty()) {
            log.error("User with given credentials not found");
            throw new ResourceNotFoundException("User", "username or email", usernameOrEmail);
        }
        return user.get();
    }

    public Boolean isExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public Boolean isExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void isUserAuthorized(Long userId, User currentUser) {
        if (!userId.equals(currentUser.getUserId())) {
            log.error("ActionForbiddenException. Action forbidden for current user");
            throw new ActionForbiddenException("Action forbidden for current user");
        }
    }

    public Boolean isAdmin(String username) {
        UserDetails details = userDetailsService.loadUserByUsername(username);
        return details != null && details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    public Boolean usernameAlreadyExists(String username) {
        String formattedUsername = username.trim().replaceAll("\\s+", "").toLowerCase();
        return userRepository.findAll().stream()
                .anyMatch(n -> n.getUsername().toLowerCase().equals(formattedUsername));
    }

    public Boolean isRoleExistsByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        return role.isPresent();
    }
}
