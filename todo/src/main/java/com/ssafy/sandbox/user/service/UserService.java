package com.ssafy.sandbox.user.service;

import com.ssafy.sandbox.user.domain.User;
import com.ssafy.sandbox.user.dto.KakaoUserInfoResponseDto;
import com.ssafy.sandbox.user.dto.LoginResponseDto;
import com.ssafy.sandbox.user.dto.ReIssueTokenDto;
import com.ssafy.sandbox.user.dto.VerifyTokenResponseDto;
import com.ssafy.sandbox.user.repository.UserRepository;
import com.ssafy.sandbox.user.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;

    public LoginResponseDto getToken(KakaoUserInfoResponseDto userInfo) {
        if (!isUser(userInfo.getId())) {
            save(userInfo);
        }

        return jwtUtil.getTokens(userInfo.getId());
    }

    private void save(KakaoUserInfoResponseDto userInfo) {
        User newUser = User.builder()
                .id(userInfo.getId())
                .nickname(userInfo.getProperties().getNickname())
                .build();
        userRepository.save(newUser);
    }

    private boolean isUser(Long id) {
        return userRepository.existsById(id);
    }

    public VerifyTokenResponseDto getNickname(String token) {
        String[] data = token.split(" ");
        token = data[1];

        Long userId = jwtUtil.getUserId(token);
        User user = userRepository.findById(userId)
                .orElseThrow();
        return new VerifyTokenResponseDto(user.getNickname());
    }

    public ReIssueTokenDto reissueToken(String token) {
        String accessToken = jwtUtil.reissue(token);
        return new ReIssueTokenDto(accessToken);
    }
}
