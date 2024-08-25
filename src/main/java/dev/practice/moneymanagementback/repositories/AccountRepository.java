package dev.practice.moneymanagementback.repositories;

import dev.practice.moneymanagementback.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
