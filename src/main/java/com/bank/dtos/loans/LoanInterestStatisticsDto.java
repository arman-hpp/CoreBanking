package com.bank.dtos.loans;

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
public final class LoanInterestStatisticsDto extends BaseDto {
    private Currencies currency;
    private BigDecimal sumInterest;
}
