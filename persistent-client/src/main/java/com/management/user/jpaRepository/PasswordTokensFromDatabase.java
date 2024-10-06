package com.management.user.jpaRepository;

import com.management.user.Exceptions.EntityNotFoundException;
import com.management.user.dto.PasswordTokenDto;
import com.management.user.entity.PasswordTokens;
import com.management.user.repo.PasswordTokenAdapter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PasswordTokensFromDatabase implements PasswordTokenAdapter {
    private final PasswordTokensRepository passwordTokensRepository;

    public PasswordTokensFromDatabase(PasswordTokensRepository passwordTokensRepository) {
        this.passwordTokensRepository = passwordTokensRepository;
    }

    @Override
    @Transactional
    public void savePasswordTokens(PasswordTokenDto passwordTokenDto) {
        PasswordTokens passwordTokenToBeSaved = PasswordTokens.mapDtoToEntity(passwordTokenDto);
        Optional<PasswordTokens> passwordTokenPresentInDb = passwordTokensRepository.findByUserName(passwordTokenToBeSaved.getUserName());
        passwordTokenPresentInDb.ifPresent(passwordTokensRepository::delete);
        passwordTokensRepository.save(passwordTokenToBeSaved);
    }


    @Override
    public PasswordTokenDto fetchPasswordToken(String email) {
        PasswordTokens token = passwordTokensRepository.findByUserName(email)
                .orElseThrow(() -> new EntityNotFoundException("Password token not found"));
        return PasswordTokens.mapEntitiToDto(token);
    }
}
