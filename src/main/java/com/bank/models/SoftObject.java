package com.bank.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class SoftObject extends VersionedObject {
    @Column(name = "deleted")
    private Boolean deleted = false;
}
