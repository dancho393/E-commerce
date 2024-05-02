package com.projects.ecommerce.model.repository;

import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.VerificationToken;
import jakarta.persistence.Lob;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    void deleteAllByUser(User user);
}
