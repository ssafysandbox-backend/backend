package com.ssafy.sandbox.email.service;

import com.ssafy.sandbox.email.domain.SandboxEmail;
import com.ssafy.sandbox.email.dto.EmailAuthRequest;
import com.ssafy.sandbox.email.dto.EmailAuthResponse;
import com.ssafy.sandbox.email.dto.EmailSendRequest;
import com.ssafy.sandbox.email.dto.EmailSendResponse;
import com.ssafy.sandbox.email.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final EmailRepository emailRepository;

    public EmailSendResponse sendEmail(EmailSendRequest emailSendRequest) {
        String email = emailSendRequest.getEmail();
        String authCode = generateAuthCode();

        try{
//          이메일 발송
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("인증번호 발송");
            mimeMessageHelper.setText("인증번호는 " + authCode + " 입니다.");
            mailSender.send(mimeMessage);

            SandboxEmail sandboxEmail = emailRepository.findByEmail(email);
            if(sandboxEmail != null){
                sandboxEmail.updateAuthcode(authCode);
            } else if(sandboxEmail == null){
                sandboxEmail = SandboxEmail.of(email, authCode);
            }
            emailRepository.save(sandboxEmail);
            return EmailSendResponse.of(true);
        } catch (MessagingException e) {
            return EmailSendResponse.of(false);
        }
    }

    private String generateAuthCode() {
        int authCode = (int)(Math.random() * (90000)) + 100000;
        return String.valueOf(authCode);
    }

    public EmailAuthResponse authEmail(EmailAuthRequest emailAuthRequest) {
        SandboxEmail sandboxEmail = emailRepository.findByEmail(emailAuthRequest.getEmail());
        boolean isSuccess = false;
        if(emailAuthRequest.getEmail().equals(sandboxEmail.getEmail()) && sandboxEmail.getExpireDate().isAfter(LocalDateTime.now())){
            isSuccess = true;
        } else if(!emailAuthRequest.getEmail().equals(sandboxEmail.getEmail()) || sandboxEmail.getExpireDate().isBefore(LocalDateTime.now())){
            isSuccess = false;
        }
        return EmailAuthResponse.builder()
                .isSuccess(isSuccess)
                .build();
    }

//  하루에 한 번(00시) 인증 시간이 지난 이메일 데이터를 DB에서 삭제
    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void autoDeleteEmail() {
        log.debug("delete test");
        emailRepository.deleteAllByExpireDateLessThan(LocalDateTime.now());
    }
}
