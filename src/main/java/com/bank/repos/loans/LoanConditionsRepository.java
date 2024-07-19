package com.bank.repos.loans;

import com.bank.enums.accounts.Currencies;
import com.bank.models.loans.LoanCondition;
import org.springframework.stereotype.Repository;
import com.bank.repos.BaseRepository;

import java.util.Optional;

@Repository
public interface LoanConditionsRepository extends BaseRepository<LoanCondition, Long> {
    Optional<LoanCondition> findTop1ByCurrencyAndExpireDateIsNullOrderByStartDateDesc(Currencies currency);
}
