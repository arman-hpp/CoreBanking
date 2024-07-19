package com.bank.dtos.users;

import com.bank.enums.users.UserTypes;
import com.bank.dtos.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class UserDto extends BaseDto {
    private Long id;
    private String username;
    private String password;
    private UserTypes userType;
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime lastLoginDate;
    @DateTimeFormat(pattern ="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime lastPasswordChangedDate;
}
