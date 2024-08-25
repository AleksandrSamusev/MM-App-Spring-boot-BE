package dev.practice.moneymanagementback.mappers;

import dev.practice.moneymanagementback.dtos.NewExpenseDto;
import dev.practice.moneymanagementback.models.Account;
import dev.practice.moneymanagementback.models.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    public static Expense toExpense(NewExpenseDto dto, Account account) {
        Expense expense = new Expense();
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setAccount(account);
        expense.setAmount(dto.getAmount());
        expense.setPaymentTo(dto.getPaymentTo());
        expense.setCurrentBalance(account.getCurrentBalance());
        return expense;
    }
}
