package com.management.user.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginInput {
    @Schema(description = "Employee Email", example = "Vishu@gmail.com")
    public String email;
    @Schema(description = "Employee Password", example = "asjak@ada")
    public String password;
}
