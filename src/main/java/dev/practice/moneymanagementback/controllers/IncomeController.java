package dev.practice.moneymanagementback.controllers;

import dev.practice.moneymanagementback.dtos.NewIncomeDto;
import dev.practice.moneymanagementback.models.Expense;
import dev.practice.moneymanagementback.models.Income;
import dev.practice.moneymanagementback.services.IncomeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/incomes")
@Validated
public class IncomeController {

    private final IncomeService incomeService;

    @PostMapping("/accounts/{accountId}")
    ResponseEntity<Income> createIncome(@RequestBody @Valid NewIncomeDto dto, @PathVariable Long accountId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(incomeService.createIncome(dto, accountId));
    }

    @GetMapping("/{accountId}")
    List<Income> getIncomesByAccountIdAndDate(@PathVariable Long accountId,
                                              @RequestParam String month) {
        return incomeService.getIncomesByAccountIdAndDate(accountId, month);
    }

}
