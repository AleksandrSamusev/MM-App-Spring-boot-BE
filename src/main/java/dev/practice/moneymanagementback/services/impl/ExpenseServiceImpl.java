package dev.practice.moneymanagementback.services.impl;

import dev.practice.moneymanagementback.dtos.NewExpenseDto;
import dev.practice.moneymanagementback.exception.InvalidParameterException;
import dev.practice.moneymanagementback.exception.ResourceNotFoundException;
import dev.practice.moneymanagementback.mappers.ExpenseMapper;
import dev.practice.moneymanagementback.models.Account;
import dev.practice.moneymanagementback.models.Expense;
import dev.practice.moneymanagementback.repositories.AccountRepository;
import dev.practice.moneymanagementback.repositories.ExpenseRepository;
import dev.practice.moneymanagementback.services.ExpenseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {

    private final AccountRepository accountRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    public Expense createExpense(NewExpenseDto dto, Long accountId) {
        if (accountId == null) {
            throw new InvalidParameterException("Account id cannot be null");
        }
        if (dto == null) {
            throw new InvalidParameterException("DTO cannot be null");
        }
        Account account = accountRepository.findById(accountId).orElseThrow(() ->
                new ResourceNotFoundException("Account", "account ID", accountId.toString()));
        double amount = account.getCurrentBalance() - dto.getAmount();
        Double truncatedAmount = Math.floor(amount * 100) / 100;
        account.setCurrentBalance(truncatedAmount);
        Account savedAccount = accountRepository.save(account);
        log.info("New expense with amount {} was saved to the account id = {}",
                dto.getAmount(), savedAccount.getAccountId());
        return expenseRepository.save(ExpenseMapper.toExpense(dto, savedAccount));
    }


    @Override
    public List<Expense> getExpensesByAccountIdAndDate(Long accountId, String month) {


        String startDateStr = "";
        String endDateStr = "";

        if (!month.toLowerCase().contains("q")) {
            String monthAsNum = monthAsNumber(month);
            startDateStr = "01-" + monthAsNum + "-2024";
            endDateStr = "31-" + monthAsNum + "-2024";
        } else {
            if (month.equals("q1")) {
                startDateStr = "01-01-2024";
                endDateStr = "31-03-2024";
            }
            if (month.equals("q2")) {
                startDateStr = "01-04-2024";
                endDateStr = "31-06-2024";
            }
            if (month.equals("q3")) {
                startDateStr = "01-07-2024";
                endDateStr = "31-09-2024";
            }
            if (month.equals("q4")) {
                startDateStr = "01-10-2024";
                endDateStr = "31-12-2024";
            }
        }

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDate = LocalDate.parse(startDateStr, df);
        LocalDate endDate = LocalDate.parse(endDateStr, df);

        return expenseRepository.getAllByAccountIdAndMonth(accountId, startDate, endDate);
    }

    private String monthAsNumber(String month) {
        if (month.equals("January")) {
            return "01";
        }
        if (month.equals("February")) {
            return "02";
        }
        if (month.equals("March")) {
            return "03";
        }
        if (month.equals("April")) {
            return "04";
        }
        if (month.equals("May")) {
            return "05";
        }
        if (month.equals("June")) {
            return "06";
        }
        if (month.equals("July")) {
            return "07";
        }
        if (month.equals("August")) {
            return "08";
        }
        if (month.equals("September")) {
            return "09";
        }
        if (month.equals("October")) {
            return "10";
        }
        if (month.equals("November")) {
            return "11";
        }
        if (month.equals("December")) {
            return "12";
        }
        return null;
    }
}
