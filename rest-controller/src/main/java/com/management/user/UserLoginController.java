package com.management.user;

import com.management.user.models.LoginInput;
import com.management.user.models.PasswordCreateInput;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("api/v1/user")
public class UserLoginController {
    private final UserLogin userLogin;

    public UserLoginController(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @PostMapping(value = "/save", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address saved successfully"),
            @ApiResponse(responseCode = "404", description = "Employee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<AuthResponse> createLoginPassword(@RequestBody PasswordCreateInput passwordCreateInput) throws Exception {
        if(Objects.nonNull(userLogin.createLoginPassword(passwordCreateInput))){
            return ResponseEntity.ok(new AuthResponse("success", "Login successful", null));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new AuthResponse("error", "Invalid email or phone number", null));
    }

    @PostMapping(value = "/validate", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address saved successfully"),
            @ApiResponse(responseCode = "404", description = "Password token not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<AuthResponse> validateLoginPassword(@RequestBody LoginInput loginInput){
        try {
            if(!userLogin.validateLoginPassword(loginInput).isEmpty()){
                return ResponseEntity.ok(new AuthResponse("success", "Login successful", userLogin.validateLoginPassword(loginInput)));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("error", "Password did not match / Password validity expired", null));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new AuthResponse("error", e.getMessage(), null));
        }
    }
}
