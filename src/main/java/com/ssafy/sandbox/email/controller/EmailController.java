package com.ssafy.sandbox.email.controller;

import com.ssafy.sandbox.email.dto.EmailAuthRequest;
import com.ssafy.sandbox.email.dto.EmailAuthResponse;
import com.ssafy.sandbox.email.dto.EmailSendRequest;
import com.ssafy.sandbox.email.dto.EmailSendResponse;
import com.ssafy.sandbox.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;
    @PostMapping()
    public ResponseEntity<EmailSendResponse> sendEmail(@RequestBody EmailSendRequest emailSendRequest) {
        log.debug("Sending email: {}", emailSendRequest.getEmail());
        EmailSendResponse emailSendResponse = emailService.sendEmail(emailSendRequest);
        return ResponseEntity.ok().body(emailSendResponse);
    }

    @PostMapping("/authentication")
    public ResponseEntity<EmailAuthResponse> authEmail(@RequestBody EmailAuthRequest emailAuthRequest) {
        EmailAuthResponse emailAuthResponse = emailService.authEmail(emailAuthRequest);
        return ResponseEntity.ok(emailAuthResponse);
    }
}
