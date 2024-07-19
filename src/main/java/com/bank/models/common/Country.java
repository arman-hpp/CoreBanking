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
@Table(name = "Countries")
public class Country extends BaseEntity {
    @Column(name = "name", length = 80)
    private String name;
    @Column(name = "two_letter_iso_code", length = 2)
    private String twoLetterIsoCode;
    @Column(name = "three_letter_iso_code", length = 3)
    private String threeLetterIsoCode;
    @Column(name = "numeric_iso_code")
    private Integer numericIsoCode;
    @Column(name = "display_order")
    private Integer displayOrder;
    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Province> provinces = new HashSet<>();
}
