package com.canello.emailservice.controllers;

import com.canello.emailservice.application.EmailSenderService;
import com.canello.emailservice.core.EmailRequest;
import com.canello.emailservice.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/email")
public class EmailSenderController {

    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailSenderController(EmailSenderService emailService){
        this.emailSenderService = emailService;
    }

    @PostMapping()
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request){
        try{
            this.emailSenderService.sendEmail(request.to(), request.subject(), request.body());
            return  ResponseEntity.ok("email enviado com sucesso");
        }catch (EmailServiceException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("erro no envio do email");
        }
    }
}
