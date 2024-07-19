package com.bank.dtos.users;

import com.bank.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class UserRegisterInputDto extends BaseDto {
    private String username;
    private String password;
    private String repeatPassword;
    private Boolean agreeTerms;

    public UserRegisterInputDto(String username) {
        setUsername(username);
    }
}
