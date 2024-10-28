package com.ssafy.sandbox.email.dto;

import lombok.Builder;

@Builder
public class VerifyEmailDto {

    boolean isSuccess;

    public boolean getIsSuccess() {
        return this.isSuccess;
    }
}
