package com.bank.models;

import jakarta.persistence.Column;
import jakarta.persistence.Version;

/* Enable Optimistic Locking on Write Operations */
public abstract class BaseVersionedEntity extends BaseEntity {
    @Version
    @Column(name = "version")
    private Long version;
}
