package com.bank.dtos.accounts;

import com.bank.enums.accounts.AccountTypes;
import com.bank.enums.accounts.Currencies;
import com.bank.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class AccountDto extends BaseDto {
    private Long id;
    private BigDecimal balance;
    private Currencies currency;
    private AccountTypes accountType;
    private Long customerId;
    private String customerName;
}