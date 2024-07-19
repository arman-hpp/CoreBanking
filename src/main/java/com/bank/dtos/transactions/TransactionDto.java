package com.bank.dtos.transactions;

import com.bank.enums.accounts.Currencies;
import com.bank.enums.transactions.TransactionTypes;
import com.bank.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class TransactionDto extends BaseDto {
    private Long id;
    private BigDecimal amount;
    private TransactionTypes transactionType;
    private LocalDateTime regDate;
    private String description;
    private Long accountId;
    private Long userId;
    private String traceNo;
    private Currencies currency;
    private String accountCustomerName;
    private BigDecimal accountBalance;
    private Currencies accountCurrency;

    public TransactionDto(BigDecimal amount, TransactionTypes transactionType, String description,
                          Long accountId, Long userId, String traceNo, Currencies currency) {
        setAmount(amount);
        setTransactionType(transactionType);
        setDescription(description);
        setAccountId(accountId);
        setUserId(userId);
        setTraceNo(traceNo);
        setCurrency(currency);
    }
}