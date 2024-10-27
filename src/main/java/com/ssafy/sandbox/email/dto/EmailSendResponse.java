package com.ssafy.sandbox.email.dto;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class EmailSendResponse {

    private boolean isOk;

    public static EmailSendResponse of(boolean isOk){
        return EmailSendResponse.builder()
                .isOk(isOk)
                .build();
    }

    public boolean getIsOk(){
        return isOk;
    }
}
