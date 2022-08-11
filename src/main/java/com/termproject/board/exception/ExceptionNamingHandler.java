package com.termproject.board.exception;

public class ExceptionNamingHandler {
    public static final String USERNAME_ERROR = "닉네임은 최소 4자 이상, 12자 이하 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성되어야 합니다.";
    public static final String PASSWORD_ERROR = "비밀번호는 최소 4자 이상이며, 32자 이하 알파벳 소문자(a~z), 숫자(0~9) 로 구성되어야 합니다.";
    public static final String NICKNAME_OVERLAP_ERROR = "중복된 닉네임입니다.";

    public static final String BAD_CREDENTIAL_ACCESS = "잘못된 접근입니다.";


    public static final String TOKEN_EXPIRED = "Access Token 이 만료되었습니다.";
    public static final String TOKEN_VERIFIED_FAIL = "잘못된 Access Token 입니다.";
    public static final String NOT_LOGIN = "로그인이 필요한 서비스입니다.";

    public static final String CANNOT_FIND_BOARD = "Can not find Board";
    public static final String CANNOT_FIND_USERNAME = "Can not find username";
}
