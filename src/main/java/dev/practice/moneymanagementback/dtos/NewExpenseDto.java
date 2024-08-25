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
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewExpenseDto {
    @JsonFormat(pattern="dd-MM-yyyy")
    @NotNull(message = "Expense date cannot be null")
    @PastOrPresent(message = "Expense date should be in Past or Present")
    private LocalDate expenseDate;

    @NotNull(message = "Amount cannot be null")
    @Positive
    private Double amount;

    @NotBlank(message = "Payment to cannot be blank")
    @Length(max = 150)
    private String paymentTo;
}
