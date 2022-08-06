package com.termproject.board.config.jwt.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.termproject.board.config.auth.PrincipalDetails;
import com.termproject.board.config.jwt.token.properties.AccessTokenProperties;
import com.termproject.board.config.jwt.token.properties.CommonTokenProperties;
import com.termproject.board.domain.user.User;
import lombok.Getter;

import java.util.Date;

@Getter
public class ResponseToken {
    private String accessToken;
    private PrincipalDetails principalDetails;

    public ResponseToken(PrincipalDetails principalDetails){
        this.principalDetails = principalDetails;
        accessToken = makeToken("accessToken", AccessTokenProperties.EXPIRE_TIME);
    }

    public ResponseToken(String name){
        User user = User.builder().username(name).build();
        this.principalDetails = new PrincipalDetails(user);
        accessToken = makeToken("accessToken", AccessTokenProperties.EXPIRE_TIME);
    }


    public String makeToken(String tokenName, int expire){
        return JWT.create()
                .withSubject(tokenName)
                .withExpiresAt(new Date(System.currentTimeMillis() + expire ))
                .withClaim("username", principalDetails.getUser().getUsername())
                .sign(Algorithm.HMAC512(CommonTokenProperties.SECRET));
    }
}
