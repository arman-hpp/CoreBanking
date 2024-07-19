package com.bank.models;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseSoftEntity extends BaseEntity {
    @Column(name = "deleted")
    private Boolean deleted = false;
}
