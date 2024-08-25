package dev.practice.moneymanagementback.controllers;

import dev.practice.moneymanagementback.dtos.NewAccountDto;
import dev.practice.moneymanagementback.models.Account;
import dev.practice.moneymanagementback.services.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    ResponseEntity<List<Account>> getAllUsersAccounts(@AuthenticationPrincipal UserDetails userDetails,
                                                      @RequestParam(required = false) String month) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountService.getAllUsersAccounts(userDetails.getUsername(), month));
    }

    @PostMapping
    ResponseEntity<Account> createAccount(@RequestBody @Valid NewAccountDto dto,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountService.createAccount(dto, userDetails.getUsername()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{accountId}")
    ResponseEntity<String> deleteAccountById(@PathVariable  Long accountId) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(accountService.deleteAccountById(accountId));
    }

}
