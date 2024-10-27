package com.ssafy.sandbox.email.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name="sandbox_emails")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class SandboxEmail {
    @Id
    private String email;
    @Column(nullable = false)
    private String authCode;
    private LocalDateTime expireDate;

    private static final int MAX_EXPIRE_TIME = 5;

    public static SandboxEmail of(String email, String authCode) {
        return SandboxEmail.builder()
                .email(email)
                .authCode(authCode)
                .expireDate(LocalDateTime.now().plusMinutes(MAX_EXPIRE_TIME))
                .build();
    }

    public void updateAuthcode(String authCode){
        this.authCode = authCode;
        this.expireDate = LocalDateTime.now().plusMinutes(MAX_EXPIRE_TIME);
    }
}