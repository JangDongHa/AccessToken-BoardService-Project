package com.termproject.board.exception;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String proceedAllException(Exception e){
        return String.format("<h1>%s</h1>", e.getMessage());
    }

    @ExceptionHandler(value = TokenExpiredException.class)
    public String tokenExpiredException(TokenExpiredException e){
        return ExceptionNamingHandler.TOKEN_EXPIRED;
    }

    @ExceptionHandler(value = SignatureVerificationException.class)
    public String signatureVerificationException(SignatureVerificationException e){
        return ExceptionNamingHandler.TOKEN_VERIFIED_FAIL;
    }

    @ExceptionHandler(value = NullPointerException.class)
    public String nullPointerException(NullPointerException e){
        final String TOKEN_NULL_EXCEPTION_MESSAGE = "http.HttpServletRequest.getHeader(String)";
        if (e.getMessage().contains(TOKEN_NULL_EXCEPTION_MESSAGE)) // 로그인이 필요한 서비스에 토큰을 입력하지 않은 경우
            return ExceptionNamingHandler.NOT_LOGIN;

        return String.format("<h1>%s</h1>", e.getMessage());
    }
}
