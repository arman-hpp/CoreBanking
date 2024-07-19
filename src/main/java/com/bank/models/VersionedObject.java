package com.bank.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class VersionedObject extends AuditableObject {
    @Version
    @Column(name = "version")
    private Long version;
}
