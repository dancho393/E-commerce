package com.projects.ecommerce.service.user;

import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.VerificationToken;

public interface EmailSender {
    public void send(VerificationToken verificationToken);
}
