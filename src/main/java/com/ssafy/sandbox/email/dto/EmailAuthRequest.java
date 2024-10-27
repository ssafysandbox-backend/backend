package com.ssafy.sandbox.email.dto;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EmailAuthRequest {
    private String authentication;
    private String email;
}
