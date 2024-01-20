package com.canello.emailservice.application;

import com.canello.emailservice.adapters.EmailSenderGateway;
import com.canello.emailservice.core.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService implements EmailSenderUseCase {

 private final EmailSenderGateway emailSenderGatway;

 @Autowired
 public EmailSenderService(EmailSenderGateway emailGateway){
     this.emailSenderGatway = emailGateway;
 }

    @Override
    public void sendEmail(String to, String subject, String body) {
        this.emailSenderGatway.sendEmail(to, subject, body);
    }
}
