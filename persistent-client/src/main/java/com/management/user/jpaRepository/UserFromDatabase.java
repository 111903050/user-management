package com.management.user.jpaRepository;

import com.management.user.dto.UserDto;
import com.management.user.entity.User;
import com.management.user.repo.UserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@EnableJpaRepositories(basePackages = "com.management.user.jpaRepository")
@EntityScan("com.management.user.entity")
public class UserFromDatabase implements UserAdapter {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    public UserFromDatabase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto fetchUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(User::mapEntityToDto).orElse(null);
    }
}
