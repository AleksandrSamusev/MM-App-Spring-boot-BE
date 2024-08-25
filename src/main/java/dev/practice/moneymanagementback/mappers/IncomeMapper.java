package dev.practice.moneymanagementback.mappers;

import dev.practice.moneymanagementback.dtos.NewIncomeDto;
import dev.practice.moneymanagementback.models.Account;
import dev.practice.moneymanagementback.models.Income;
import org.springframework.stereotype.Component;

@Component
public class IncomeMapper {
    public static Income toIncome(NewIncomeDto dto, Account account) {
        Income income = new Income();
        income.setIncomeDate(dto.getIncomeDate());
        income.setAccount(account);
        income.setAmount(dto.getAmount());
        income.setPaymentFrom(dto.getPaymentFrom());
        income.setCurrentBalance(account.getCurrentBalance());
        return income;
    }
}
