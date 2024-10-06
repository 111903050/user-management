package com.management.user.repo;

import com.management.user.dto.UserDto;

public interface UserAdapter {
    public UserDto fetchUserByEmail(String email);
}
