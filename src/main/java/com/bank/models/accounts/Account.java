package com.bank.models.accounts;

import com.bank.enums.accounts.AccountTypes;
import com.bank.enums.accounts.Currencies;
import com.bank.models.transactions.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.bank.models.BaseEntity;
import com.bank.models.customers.Customer;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Account extends BaseEntity {
    @Column(name = "balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Currencies currency;

    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private AccountTypes accountType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    public Account(Long Id) {
        setId(Id);
    }
}