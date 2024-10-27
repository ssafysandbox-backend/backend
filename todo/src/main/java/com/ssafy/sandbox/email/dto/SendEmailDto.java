package com.ssafy.sandbox.email.dto;

import lombok.Builder;

@Builder
public class SendEmailDto {

    private boolean isOk;

    public boolean getIsOk() {
        return this.isOk;
    }
}
