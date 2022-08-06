package com.termproject.board.config.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.termproject.board.config.jwt.token.properties.AccessTokenProperties;
import com.termproject.board.config.jwt.token.properties.CommonTokenProperties;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Data
public class RequestToken { // Request 토큰이 왔을 때 토큰을 저장하고 토큰에 있는 값을 추출해줌
    private String accessToken;
    public RequestToken(HttpServletRequest request){
        this.accessToken = request.getHeader(AccessTokenProperties.HEADER_STRING).replace(CommonTokenProperties.TOKEN_PREFIX, "");
    }

    public Optional<String> getTokenElement(String token, String element){
        return Optional.ofNullable(JWT
                .require(Algorithm.HMAC512(CommonTokenProperties.SECRET))
                .build().verify(token)
                .getClaim(element).asString()); // 서명이 정상적으로 되었다는 건 정상처리 된 것 (여기가 검증과정인데 JWT 라이브러리가 알아서 정리해서 비교해줌)

    }

    public Optional<String> getUsername(){
        return getTokenElement(accessToken, "username");
    }




}
