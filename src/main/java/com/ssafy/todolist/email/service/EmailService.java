package com.ssafy.todolist.email.service;

import com.ssafy.todolist.email.domain.Email;
import com.ssafy.todolist.email.dto.AuthenticationDTO;
import com.ssafy.todolist.email.dto.AuthenticationResponse;
import com.ssafy.todolist.email.dto.EmailResponse;
import com.ssafy.todolist.email.repository.EmailRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender javaMailSender;
    private static final int EXPIRATION_TIME_LIMIT = 5;

    public EmailService(JavaMailSender javaMailSender, EmailRepository emailRepository) {
        this.javaMailSender = javaMailSender;
        this.emailRepository = emailRepository;
    }

    public String createAuthentication() {
        return Integer.toString((int) (Math.random() * 90000 + 100000));
    }

    public MimeMessage createMail(String email, String authenticationNumber) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(email);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("이메일 인증");
            StringBuilder body = new StringBuilder();
            body.append("<h3>요청하신 인증 번호 입니다.</h3>")
                    .append("<h1>")
                    .append(authenticationNumber)
                    .append("</h1>")
                    .append("<h3>감사합니다.</h3>");
            message.setText(body.toString(), "UTF-8", "html");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return message;
    }

    public EmailResponse sendAuthentication(String email) {
        try {
            String authentication = createAuthentication();
            javaMailSender.send(createMail(email, authentication));
            String expirationTime = LocalDateTime.now().plusMinutes(EXPIRATION_TIME_LIMIT).toString();
            emailRepository.save(new Email(email, authentication, expirationTime));
            return new EmailResponse(true);
        } catch (Exception e) {
            return new EmailResponse(false);
        }
    }

    @Transactional(readOnly = true)
    public AuthenticationResponse authenticate(AuthenticationDTO authenticationDTO) {
        Email email = emailRepository.findByEmail(authenticationDTO.getEmail());
        String expirationTime = emailRepository.findByEmail(authenticationDTO.getEmail()).getExpirationTime();
        if (expirationTime.compareTo(LocalDateTime.now().toString()) < 0) {
            return new AuthenticationResponse(false);
        }
        return new AuthenticationResponse(authenticationDTO.getAuthentication().equals(email.getAuthenticationString()));
    }

}
