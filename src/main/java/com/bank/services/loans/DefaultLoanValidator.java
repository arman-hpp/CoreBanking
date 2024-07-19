package com.bank.services.loans;

import com.bank.exceptions.DomainException;
import com.bank.dtos.loans.LoanConditionsDto;
import com.bank.dtos.loans.LoanDto;
import com.bank.services.loans.interfaces.ILoanValidator;

public class DefaultLoanValidator implements ILoanValidator {
    public void validate(LoanConditionsDto loanConditionsDto, LoanDto loanDto) {
        if (loanDto.getRefundDuration() < loanConditionsDto.getMinRefundDuration())
            throw new DomainException("error.loan.conditions.minRefundDuration.invalid");

        if (loanDto.getRefundDuration() > loanConditionsDto.getMaxRefundDuration())
            throw new DomainException("error.loan.conditions.maxRefundDuration.invalid");

        if (loanDto.getAmount().compareTo(loanConditionsDto.getMinAmount()) < 0)
            throw new DomainException("error.loan.conditions.minAmount.invalid");

        if (loanDto.getAmount().compareTo(loanConditionsDto.getMaxAmount()) > 0)
            throw new DomainException("error.loan.conditions.maxAmount.invalid");
    }
}
