package com.bank.services.loans.interfaces;

import com.bank.dtos.loans.LoanDto;
import com.bank.dtos.loans.LoanPaymentInfoDto;

public interface ILoanCalculator {
    LoanPaymentInfoDto calculate(LoanDto loan);
}
