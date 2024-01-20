package com.canello.emailservice.infra.ses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.canello.emailservice.adapters.EmailSenderGateway;
import com.canello.emailservice.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

private final AmazonSimpleEmailService amazonSimpleEmailService;

@Autowired
public  SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService){
    this.amazonSimpleEmailService = amazonSimpleEmailService;
}
    @Override
    public void sendEmail(String to, String subject, String body) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource("leandro11canello@gmail.com")
                .withDestination(new Destination().withToAddresses(to))
                .withMessage(new Message()
                        .withSubject(new Content(subject))
                        .withBody(new Body().withText(new Content(body)))
                );

        try{
            this.amazonSimpleEmailService.sendEmail(request);
        }catch (AmazonServiceException e){
            throw  new EmailServiceException("Falha ao enviar email", e);
        }
    }
}
