package com.management.user.fakes;

import com.management.user.dto.UserDto;
import com.management.user.repo.UserAdapter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@Component
public class UserInMemory implements UserAdapter {
    private final Map<String, UserDto> userByEmail = new HashMap<>();
    @Override
    public UserDto fetchUserByEmail(String email) {
        return userByEmail.get(email);
    }

    public void put(String email, UserDto userDto){
        userByEmail.put(email, userDto);
    }
}
