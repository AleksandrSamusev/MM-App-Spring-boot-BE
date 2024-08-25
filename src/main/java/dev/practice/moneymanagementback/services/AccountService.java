package dev.practice.moneymanagementback.services;

import dev.practice.moneymanagementback.dtos.NewAccountDto;
import dev.practice.moneymanagementback.models.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllUsersAccounts(String username, String month);

    Account createAccount(NewAccountDto dto, String username);

    String deleteAccountById(Long accountId);
}
