package com.projects.ecommerce.service.user;

import com.projects.ecommerce.api.exception.MailFailureException;
import com.projects.ecommerce.model.User;
import com.projects.ecommerce.model.VerificationToken;
import com.projects.ecommerce.service.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailSender{

    private final JwtService jwtService;
    private final JavaMailSender mailSender;
    @Value("${email.from}")
    private  String fromAddress;
    @Value("${email.subject}")
    private String subject;
    @Value("${account.confirm.url}")
    private String url;
    @Override
    public void send(VerificationToken verificationToken) {
        SimpleMailMessage message = getSimpleMailMessage();
        message.setTo(verificationToken.getUser().getEmail());
        message.setSubject(subject);
        //Make it app property value
        message.setText("Please follow the link below to verify your email to activate your account \n" +
                url+"/api/v1/auth/verify?token="+verificationToken.getToken());

        try{
            mailSender.send(message);
        }catch (MailException e){
            throw new MailFailureException();
        }
    }
    private SimpleMailMessage getSimpleMailMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        return message;
    }
}
