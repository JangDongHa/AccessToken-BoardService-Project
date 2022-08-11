package com.termproject.board.like.controller;



import com.termproject.board.config.jwt.token.RequestToken;
import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.board.BoardLikeRepository;
import com.termproject.board.domain.board.BoardRepository;
import com.termproject.board.domain.user.User;
import com.termproject.board.domain.user.UserRepository;
import com.termproject.board.dto.ResponseDto;
import com.termproject.board.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class LikeApiController {

    private final LikeService likeService;

    @Autowired
    private UserRepository userRepository;




//    @PostMapping("/api/user/like/board/{id}") // auth 검증  수정예정
//    @ResponseBody
//    public ResponseDto addLike(@PathVariable Long id){
//        Optional<PostTest> test = likeService.add(id);
//
//        return new ResponseDto(HttpStatus.OK,test);
//    }
//
//    @DeleteMapping ("/api/user/like/board/{id}") // auth 검증 수정예정
//    @ResponseBody
//    public ResponseDto DeleteLike(@PathVariable Long id){
//        Optional<PostTest> test = likeService.sub(id);
//
//        return new ResponseDto(HttpStatus.OK,test);
//    }

    @GetMapping("/api/user/like/board/{id}")
    public ResponseDto<String> likeBoard(@PathVariable long id, HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        String username = requestToken.getUsername().orElseThrow();

        User user = getUser(request);
        String response = username+"님의 "+ likeService.likeBoard(id,user);


        return new ResponseDto<>(HttpStatus.OK, response);
    }



    @GetMapping("/api/user/like/comment/{id}")
    public ResponseDto<String> likeComment(@PathVariable Long id,HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        String username = requestToken.getUsername().orElseThrow();
        User user = getUser(request);
        String response = username+"님의 "+ likeService.likeComment(id,user);

        return new ResponseDto<>(HttpStatus.OK, response);
    }


    @GetMapping("/api/user/like/recomment/{id}")
    public ResponseDto<String> likeRecomment(@PathVariable Long id,HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        String username = requestToken.getUsername().orElseThrow();
        User user = getUser(request);
        String response = username+"님의 "+ likeService.likeRecomment(id,user);

        return new ResponseDto<>(HttpStatus.OK, response);
    }

    private User getUser(HttpServletRequest request){
        RequestToken requestToken = new RequestToken(request);
        String username = requestToken.getUsername().orElseThrow(()->new IllegalArgumentException("Can not find username"));
        return userRepository.findByUsername(username).orElseThrow();
    }




}
