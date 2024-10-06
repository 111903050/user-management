package com.management.user;

import com.management.user.dto.PasswordEvent;
import com.management.user.dto.PasswordTokenDto;
import com.management.user.dto.UserDto;
import com.management.user.models.LoginInput;
import com.management.user.models.PasswordCreateInput;
import com.management.user.repo.PasswordTokenAdapter;
import com.management.user.repo.UserAdapter;
import com.management.user.utils.JwtUtil;
import com.management.user.utils.PasswordUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserLogin {
    private final UserAdapter userAdapter;
    private final PasswordTokenAdapter passwordTokenAdapter;
    private final EventPublisher eventPublisher;

    public UserLogin(UserAdapter userAdapter, PasswordTokenAdapter passwordTokenAdapter, EventPublisher eventPublisher) {
        this.userAdapter = userAdapter;
        this.passwordTokenAdapter = passwordTokenAdapter;
        this.eventPublisher = eventPublisher;
    }

    public String createLoginPassword(PasswordCreateInput passwordCreateInput) throws Exception {
        UserDto userDto = userAdapter.fetchUserByEmail(passwordCreateInput.email);
        if(Objects.nonNull(userDto)){
            if(Objects.equals(userDto.phone_number, passwordCreateInput.phone_number)){
                String password = PasswordUtil.createPassword();
                String hashedPassword = PasswordUtil.hashPassword(password);
                PasswordTokenDto passwordTokenDto = new PasswordTokenDto(passwordCreateInput.email, hashedPassword, LocalDateTime.now().plusDays(5L));
                passwordTokenAdapter.savePasswordTokens(passwordTokenDto);
                PasswordEvent passwordEvent = new PasswordEvent(passwordCreateInput.email,password);
                eventPublisher.publish(passwordEvent);
                return password;
            }
        }
        return null;
    }

    public String validateLoginPassword(LoginInput loginInput) throws Exception {
        PasswordTokenDto passwordTokenDto = passwordTokenAdapter.fetchPasswordToken(loginInput.getEmail());
        if(Objects.equals(loginInput.getEmail(), passwordTokenDto.getUserName())
                && PasswordUtil.checkPassword(loginInput.getPassword(), passwordTokenDto.getPassword())
                && passwordTokenDto.getExpirationDate().isAfter(LocalDateTime.now())){
            UserDto userDto = userAdapter.fetchUserByEmail(loginInput.getEmail());
            return JwtUtil.generateJwtToken(loginInput.getEmail(), userDto.getEmployee_role());
        }
        return "";
    }
}
