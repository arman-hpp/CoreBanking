package com.bank.models.common;

import com.bank.models.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Cities")
public class City extends BaseEntity {
    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "display_order")
    private Integer displayOrder;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id")
    private Province province;
}
