package com.ssafy.todolist.email.controller;

import com.ssafy.todolist.email.dto.AuthenticationDTO;
import com.ssafy.todolist.email.dto.AuthenticationResponse;
import com.ssafy.todolist.email.dto.EmailDTO;
import com.ssafy.todolist.email.dto.EmailResponse;
import com.ssafy.todolist.email.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/email")
@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping()
    public EmailResponse sendAuthentication(@RequestBody EmailDTO emailDto) {
        return emailService.sendAuthentication(emailDto.getEmail());
    }

    @PostMapping("/authentication")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationDTO authenticationDTO) {
        return emailService.authenticate(authenticationDTO);
    }

}
