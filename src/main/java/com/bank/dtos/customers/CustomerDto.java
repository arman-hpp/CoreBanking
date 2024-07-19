package com.bank.dtos.customers;

import com.bank.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class CustomerDto extends BaseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
