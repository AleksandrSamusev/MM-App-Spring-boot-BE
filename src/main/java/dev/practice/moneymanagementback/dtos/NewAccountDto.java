package dev.practice.moneymanagementback.dtos;

import dev.practice.moneymanagementback.utils.Currencies;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewAccountDto {
    private String bankName;
    private Currencies currency;
}
