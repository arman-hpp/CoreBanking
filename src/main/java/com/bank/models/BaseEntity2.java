package com.bank.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseEntity2 extends SoftObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (this == obj)
            return true;

        if (getClass() != obj.getClass())
            if (Hibernate.getClass(this) != Hibernate.getClass(obj))
                return false;

        var that = (BaseEntity2) obj;
        return id != null && Objects.equals(id , that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
