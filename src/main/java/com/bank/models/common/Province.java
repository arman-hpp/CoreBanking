package com.bank.models.common;

import com.bank.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Provinces")
public class Province extends BaseEntity {
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "abbreviation", length = 10)
    private String abbreviation;
    @Column(name = "display_order")
    private Integer displayOrder;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;
    @OneToMany(mappedBy = "province", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<City> cities = new HashSet<>();
}
