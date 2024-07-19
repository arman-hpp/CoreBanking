package com.bank.dtos.loans;

import com.bank.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class LoanPaymentInfoDto extends BaseDto {
    private BigDecimal monthlyPaymentAmount;
    private BigDecimal overPaymentAmount;
    private List<InstallmentDto> installments;
}
