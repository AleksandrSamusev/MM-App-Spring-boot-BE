package dev.practice.moneymanagementback.services.impl;

import dev.practice.moneymanagementback.dtos.NewAccountDto;
import dev.practice.moneymanagementback.exception.InvalidParameterException;
import dev.practice.moneymanagementback.exception.ResourceNotFoundException;
import dev.practice.moneymanagementback.mappers.AccountMapper;
import dev.practice.moneymanagementback.models.Account;
import dev.practice.moneymanagementback.models.Expense;
import dev.practice.moneymanagementback.models.User;
import dev.practice.moneymanagementback.repositories.AccountRepository;
import dev.practice.moneymanagementback.repositories.UserRepository;
import dev.practice.moneymanagementback.services.AccountService;
import dev.practice.moneymanagementback.services.ExpenseService;
import dev.practice.moneymanagementback.services.IncomeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    @Override
    public List<Account> getAllUsersAccounts(String username, String month) {
        if (username == null || username.isBlank()) {
            throw new InvalidParameterException("Invalid username parameter");
        }
        userRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("User", "username", username));

        if (month == null) {
            return accountRepository.findAll().stream().filter(account ->
                    account.getUser().getUsername().equals(username)).toList();
        } else {
            List<Account> accounts = accountRepository.findAll().stream().filter(account ->
                    account.getUser().getUsername().equals(username)).toList();

            for (Account account : accounts) {
                account.setExpenses(
                        new HashSet<>(expenseService.getExpensesByAccountIdAndDate(account.getAccountId(), month)));
                account.setIncomes(
                        new HashSet<>(incomeService.getIncomesByAccountIdAndDate(account.getAccountId(), month)));
            }
            return accounts;
        }
    }

    @Override
    public Account createAccount(NewAccountDto dto, String username) {
        if (username == null || dto == null || username.isBlank()) {
            throw new InvalidParameterException("Invalid username parameter");
        }
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("User", "username", username));
        Account savedAccount = accountRepository.save(AccountMapper.toAccount(dto, user));
        log.info("New account created: {}", savedAccount);
        return savedAccount;
    }

    @Override
    public String deleteAccountById(Long accountId) {
        if (accountId == null) {
            throw new InvalidParameterException("Account id cannot be NULL");
        }
        if (accountRepository.findById(accountId).isEmpty()) {
            return "Account with id = " + accountId + " wasn't found";
        }
        accountRepository.deleteById(accountId);
        return "Account with ID = " + accountId + " was successfully deleted";
    }
}
