package com.management.user.dto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @Schema(description = "Employee Id", example = "EMP0001")
    public String id;
    @Schema(description = "Employee FirstName", example = "Vishu")
    public String first_name;
    @Schema(description = "Employee LastName", example = "Waghmare")
    public String last_name;
    @Schema(description = "Employee Email", example = "Vishu@gmail.com")
    public String email;
    @Schema(description = "Employee Role", example = "SSE")
    public String employee_role;
    @Schema(description = "Employee PhoneNumber", example = "827198739123")
    public String phone_number;
    @Schema(description = "Employee HireDate", example = "2024-09-17")
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    public LocalDate hire_date;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Schema(description = "Employee DateOfBirth", example = "2024-09-17")
    public LocalDate date_of_birth;
}
