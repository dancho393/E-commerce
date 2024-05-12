package com.projects.ecommerce.service.user;

import com.projects.ecommerce.api.model.user.changepassword.ChangeUserPasswordOperation;
import com.projects.ecommerce.api.model.user.changepassword.ChangeUserPasswordRequest;
import com.projects.ecommerce.api.model.user.changepassword.ChangeUserPasswordResponse;
import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeUserPasswordService implements ChangeUserPasswordOperation {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ChangeUserPasswordResponse process(ChangeUserPasswordRequest request) {
        User user = request.user();
        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);
        return new ChangeUserPasswordResponse("Password changed successfully:"+user.getPassword());
    }
}
