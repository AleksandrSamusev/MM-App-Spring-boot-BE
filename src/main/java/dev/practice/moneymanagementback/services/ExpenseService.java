package dev.practice.moneymanagementback.services;

import dev.practice.moneymanagementback.dtos.NewExpenseDto;
import dev.practice.moneymanagementback.models.Expense;

import java.util.List;

public interface ExpenseService {
    Expense createExpense(NewExpenseDto dto, Long accountId);

    List<Expense> getExpensesByAccountIdAndDate(Long accountId, String month);
}
