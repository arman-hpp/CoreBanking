package com.bank.dtos.users;

import com.bank.dtos.BaseDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public final class UserLoginInputDto extends BaseDto {
    @NonNull
    private String username;
    private String password;
    private String captchaImage;
    private String captcha;
}
