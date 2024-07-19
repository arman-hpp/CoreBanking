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
public final class UserChangePasswordInputDto extends BaseDto {
    private Long id;
    private String oldPassword;
    private String newPassword;
    private String repeatNewPassword;
}
