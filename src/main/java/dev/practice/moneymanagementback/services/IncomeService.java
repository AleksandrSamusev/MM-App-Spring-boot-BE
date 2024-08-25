package dev.practice.moneymanagementback.services;

import dev.practice.moneymanagementback.dtos.NewIncomeDto;
import dev.practice.moneymanagementback.models.Income;

import java.util.List;

public interface IncomeService {
    Income createIncome(NewIncomeDto dto, Long accountId);

    List<Income> getIncomesByAccountIdAndDate(Long accountId, String month);
}
