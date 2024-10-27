package com.ssafy.sandbox.email.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EmailAuthResponse {

    private final boolean isSuccess;

    public boolean getIsSuccess() {
        return isSuccess;
    }


}
