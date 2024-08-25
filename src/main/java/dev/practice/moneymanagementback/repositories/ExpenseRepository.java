package dev.practice.moneymanagementback.repositories;

import dev.practice.moneymanagementback.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("select e from Expense e where e.expenseDate <= :endDate " +
            "and e.expenseDate >= :startDate and e.account.accountId = :accountId")
    List<Expense> getAllByAccountIdAndMonth(
            @Param("accountId") Long accountId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
