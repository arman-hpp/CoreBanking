package com.bank.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterDto extends BaseDto {
    private String columnName;

    private String compareOperation;

    private Object columnValue;
}
