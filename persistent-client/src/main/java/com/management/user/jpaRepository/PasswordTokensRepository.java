package com.management.user.jpaRepository;

import com.management.user.entity.PasswordTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordTokensRepository extends JpaRepository<PasswordTokens,Integer> {

    Optional<PasswordTokens> findByUserName(String userName);
}
