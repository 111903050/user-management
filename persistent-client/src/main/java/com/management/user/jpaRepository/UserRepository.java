package com.management.user.jpaRepository;

import com.management.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
@Service
public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);
}
