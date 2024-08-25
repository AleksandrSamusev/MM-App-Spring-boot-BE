package dev.practice.moneymanagementback.mappers;

import dev.practice.moneymanagementback.dtos.NewAccountDto;
import dev.practice.moneymanagementback.models.Account;
import dev.practice.moneymanagementback.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;

@Component
public class AccountMapper {

    public static Account toAccount (NewAccountDto dto, User user) {
        Account account = new Account();
        account.setBankName(dto.getBankName());
        account.setUser(user);
        account.setCreatedAt(LocalDateTime.now());
        account.setCurrency(dto.getCurrency());
        account.setCurrentBalance(0.0);
        account.setIncomes(new HashSet<>());
        account.setExpenses(new HashSet<>());
        return account;
    }
}
