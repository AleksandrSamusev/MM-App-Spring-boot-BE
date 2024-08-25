package dev.practice.moneymanagementback.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "incomes")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "income_id")
    private Long incomeId;

    @Column(name = "income_date")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate incomeDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_from")
    private String paymentFrom;

    @Column(name = "current_balance")
    private Double currentBalance;
}
