package com.management.user.repo;

import com.management.user.dto.PasswordTokenDto;
import com.management.user.models.LoginInput;

public interface PasswordTokenAdapter {
    void savePasswordTokens(PasswordTokenDto passwordTokenDto);

    PasswordTokenDto fetchPasswordToken(String email);
}
