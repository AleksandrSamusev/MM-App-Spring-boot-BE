package dev.practice.moneymanagementback.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewIncomeDto {

    @JsonFormat(pattern="dd-MM-yyyy")
    @NotNull(message = "Income date cannot be null")
    @PastOrPresent(message = "Income date should be in Past or Present")
    private LocalDate incomeDate;

    @NotNull(message = "Amount cannot be null")
    @Positive
    private Double amount;

    @NotBlank(message = "Payment from cannot be blank")
    private String paymentFrom;
}
