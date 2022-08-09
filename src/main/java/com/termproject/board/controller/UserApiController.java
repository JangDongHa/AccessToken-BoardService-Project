package com.termproject.board.controller;

import com.termproject.board.config.auth.PrincipalDetails;
import com.termproject.board.config.jwt.token.RequestToken;
import com.termproject.board.dto.RequestUserDto;
import com.termproject.board.dto.ResponseDto;
import com.termproject.board.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserApiController {
    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/api/user/join")
    public ResponseDto<String> joinUser(@RequestBody RequestUserDto requestUserDto){
        return userService.joinUser(requestUserDto);
    }

    // RequestToken 을 이용하여 로그인정보를 알아내는 메서드 예시
    // 해당 메서드를 참조하여 로그인정보가 필요하면 RequestToken 에 getUsername 으로 받아내서 사용
    @GetMapping("/api/board/test")
    public String test(HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        String username = requestToken.getUsername().orElseThrow();

        return "<h1>Hi</h1>";
    }
}
