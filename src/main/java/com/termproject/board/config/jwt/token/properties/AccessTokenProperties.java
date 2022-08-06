package com.termproject.board.config.jwt.token.properties;

public interface AccessTokenProperties extends CommonTokenProperties{
    int EXPIRE_TIME = 1000 * 60 * 30; // 만료시간(1000 = 1초) : 현재시간 + 30분
    String HEADER_STRING = "Authorization";
}
