package dev.practice.moneymanagementback.controllers;

import dev.practice.moneymanagementback.dtos.NewExpenseDto;
import dev.practice.moneymanagementback.models.Expense;
import dev.practice.moneymanagementback.services.ExpenseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/accounts/{accountId}")
    ResponseEntity<Expense> createExpense(@RequestBody @Valid NewExpenseDto dto, @PathVariable Long accountId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expenseService.createExpense(dto, accountId));
    }

    @GetMapping("/{accountId}")
    List<Expense> getExpensesByAccountIdAndDate(@PathVariable Long accountId,
                                                @RequestParam String month) {
        return expenseService.getExpensesByAccountIdAndDate(accountId, month);
    }

}
