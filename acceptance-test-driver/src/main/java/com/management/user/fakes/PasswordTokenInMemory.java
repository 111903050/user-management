package com.management.user.fakes;

import com.management.user.dto.PasswordTokenDto;
import com.management.user.repo.PasswordTokenAdapter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
@Component
public class PasswordTokenInMemory implements PasswordTokenAdapter {
    private final Map<String, PasswordTokenDto> passwordTokenDtoMap = new HashMap<>();

    @Override
    public void savePasswordTokens(PasswordTokenDto passwordTokenDto) {
        passwordTokenDtoMap.put(passwordTokenDto.getUserName(), passwordTokenDto);
    }

    @Override
    public PasswordTokenDto fetchPasswordToken(String email) {
        return passwordTokenDtoMap.get(email);
    }

    public PasswordTokenDto createPassword(){
        return new PasswordTokenDto(
                "vishusw24@gmail.com",
                "password",
                LocalDateTime.of(2001,04,11,0,0,0)
        );
    }
}
