package com.projects.ecommerce.service.verificationtoken;

import com.projects.ecommerce.api.exception.MailFailureException;
import com.projects.ecommerce.api.model.passwordresettoekn.sendEmail.SendPasswordTokenOperation;
import com.projects.ecommerce.api.model.passwordresettoekn.sendEmail.SendPasswordTokenRequest;
import com.projects.ecommerce.api.model.passwordresettoekn.sendEmail.SendPasswordTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendPasswordTokenService implements SendPasswordTokenOperation {
    private final MailSender mailSender;
    @Value("${email.from}")
    private  String fromAddress;
    @Value("${email.subject}")
    private String subject;
    @Value("${password.reset.url}")
    private String url;
    @Override
    public SendPasswordTokenResponse process(SendPasswordTokenRequest request) {
        SimpleMailMessage message = getSimpleMailMessage();
        message.setTo(request.email());
        message.setSubject(subject);

        message.setText("Please follow the link below to reset your password: \n" +
                url+request.token());

        try{
            mailSender.send(message);
        }catch (MailException e){
            throw new MailFailureException();
        }
        return new SendPasswordTokenResponse("The password reset link has been sent to your email.");
    }
    private SimpleMailMessage getSimpleMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        return message;
    }
}
