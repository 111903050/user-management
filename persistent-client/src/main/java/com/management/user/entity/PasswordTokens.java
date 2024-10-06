package com.management.user.entity;

import com.management.user.dto.PasswordTokenDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "JWT_PASSWORD_TOKENS")
public class PasswordTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JPT_ID")
    public Integer id;

    @Column(name = "JPT_USER_NAME")
    public String userName;

    @Column(name = "JPT_PASSWORD")
    public String password;

    @Column(name = "JPT_EXPIRATION_DATE")
    public LocalDateTime expirationDate;

    public PasswordTokens(String userName, String password, LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        this.password = password;
        this.userName = userName;
    }

    public static PasswordTokens mapDtoToEntity(PasswordTokenDto passwordTokenDto) {
        return new PasswordTokens(passwordTokenDto.getUserName(),passwordTokenDto.getPassword(),passwordTokenDto.getExpirationDate());
    }

    public static PasswordTokenDto mapEntitiToDto(PasswordTokens passwordTokenDto) {
        return new PasswordTokenDto(passwordTokenDto.getUserName(),passwordTokenDto.getPassword(),passwordTokenDto.getExpirationDate());
    }
}
