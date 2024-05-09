package com.projects.ecommerce.service.user;

import com.projects.ecommerce.api.exception.TokenNotFound;
import com.projects.ecommerce.api.exception.UserAlreadyExistsException;
import com.projects.ecommerce.api.model.user.verifyemail.VerifyEmailOperation;
import com.projects.ecommerce.api.model.user.verifyemail.VerifyEmailRequest;
import com.projects.ecommerce.api.model.user.verifyemail.VerifyEmailResponse;
import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.VerificationToken;
import com.projects.ecommerce.model.repository.UserRepository;
import com.projects.ecommerce.model.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyUserService implements VerifyEmailOperation {
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    @Override
    public VerifyEmailResponse process(VerifyEmailRequest request)  {
        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(request.token())
                        .orElseThrow(()->  new TokenNotFound("Token Not Found"));
        User user= verificationToken.getUser();
        user.setEmailVerified(true);
        userRepository.save(user);
        verificationTokenRepository.delete(verificationToken);
        return new VerifyEmailResponse("You Verified Your Email Token");

    }
}
