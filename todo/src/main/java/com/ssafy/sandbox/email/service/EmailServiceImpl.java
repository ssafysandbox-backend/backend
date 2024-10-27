package com.ssafy.sandbox.email.service;

import com.ssafy.sandbox.email.domain.EmailCode;
import com.ssafy.sandbox.email.repository.EmailCodeRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailCodeRepository emailCodeRepository;
    private final JavaMailSender javaMailSender;

    @Value("${MAIL.TITLE}")
    private String MAIL_TITLE;

    @Override
    public void sendEmail(String email) throws MessagingException {
        emailCodeRepository.findEmailCodeByEmail(email).ifPresent(emailCodeRepository::delete);

        String code = UUID.randomUUID().toString().substring(0, 6);
        send(email, code);

        EmailCode emailCode = new EmailCode(email, code);
        emailCodeRepository.save(emailCode);
    }

    @Override
    public boolean verify(String email, String code) throws NoSuchElementException {
        EmailCode emailCode = emailCodeRepository.findEmailCodeByEmail(email)
                .orElseThrow(NoSuchElementException::new);

        log.debug(emailCode.getEmail(), emailCode.getCode());
        return emailCode.getCode().equals(code);
    }

    private void send(String email, String code) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(email);
        helper.setSubject(MAIL_TITLE);
        helper.setText(code);

        javaMailSender.send(message);
    }
}
