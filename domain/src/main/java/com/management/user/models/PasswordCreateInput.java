package com.management.user.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PasswordCreateInput {
    @Schema(description = "Employee Email", example = "Vishu@gmail.com")
    public String email;
    @Schema(description = "Employee PhoneNumber", example = "827198739123")
    public String phone_number;
}
