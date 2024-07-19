package com.bank.models.ledgers;

import com.bank.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Ledgers")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Ledger extends BaseEntity {
    @Column(name = "code", length = 20, nullable = false, unique = true)
    private String code;

    @Column(name = "title", length = 40, nullable = false)
    private String title;

    @Column(name = "description", length = 250)
    private String description;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToMany(mappedBy = "ledger", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<GroupLedger> groupLedgers = new HashSet<>();
}
