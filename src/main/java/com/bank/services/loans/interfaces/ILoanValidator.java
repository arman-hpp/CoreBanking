package com.bank.services.loans.interfaces;

import com.bank.dtos.loans.LoanConditionsDto;
import com.bank.dtos.loans.LoanDto;

public interface ILoanValidator {
    void validate(LoanConditionsDto loanConditionsDto, LoanDto loanDto);
}
