package com.projects.ecommerce.service.verificationtoken;

import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.VerificationToken;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {

    public VerificationToken generateVerificationToken(User user,String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
        return verificationToken;
    }

}
