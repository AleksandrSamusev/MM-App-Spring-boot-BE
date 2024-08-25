package dev.practice.moneymanagementback.repositories;

import dev.practice.moneymanagementback.models.Expense;
import dev.practice.moneymanagementback.models.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("select i from Income i where i.incomeDate <= :endDate " +
            "and i.incomeDate >= :startDate and i.account.accountId = :accountId")
    List<Income> getAllByAccountIdAndMonth(
            @Param("accountId") Long accountId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

}
