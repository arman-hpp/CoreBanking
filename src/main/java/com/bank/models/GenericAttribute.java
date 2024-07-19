package com.bank.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// Entity–attribute–value model (EAV)
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class GenericAttribute extends AuditableObject {
    @Column(name = "attr_group", length = 21)
    private String group ;

    @Column(name = "attr_key", length = 21)
    private String key;

    @Column(name = "attr_value", length = 200)
    private String value;

    public Boolean getValueAsBoolean() {
        return Boolean.valueOf(getValue());
    }

    public Integer getValueAsInteger() {
        return Integer.valueOf(getValue());
    }

    public Long getValueAsLong() {
        return Long.valueOf(getValue());
    }

    public BigDecimal getValueAsBigDecimal() {
        return new BigDecimal(getValue());
    }

    public LocalDateTime getValueAsDateTime() {
        return LocalDateTime.parse(getValue());
    }
}
