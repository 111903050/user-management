package com.management.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordTokenDto {

    @Schema(name = "username")
    public String userName;

    @Schema(name = "password")
    public String password;

    @Schema(description = "expiration_date")
    public LocalDateTime expirationDate;
}
