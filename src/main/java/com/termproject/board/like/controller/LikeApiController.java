package com.termproject.board.like.controller;



import com.termproject.board.dto.ResponseDto;
import com.termproject.board.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class LikeApiController {

    private final LikeService likeService;


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

    @PostMapping("/api/user/like/board/{id}")
    public ResponseDto<String> likeBoard(@PathVariable Long id){
        likeService.likeBoard(id);

        return new ResponseDto<>(HttpStatus.OK, "게시글 좋아요 완료");
    }

    @DeleteMapping("/api/user/like/board/{id}")
    public ResponseDto<String> unlikeBoard(@PathVariable Long id){
        likeService.unlikeBoard(id);

        return new ResponseDto<>(HttpStatus.OK, "게시글 좋아요 취소");
    }





    @PostMapping("/api/user/like/comment/{id}")
    public ResponseDto<String> likeComment(@PathVariable Long id){
        likeService.likeComment(id);

        return new ResponseDto<>(HttpStatus.OK, "댓글 좋아요 완료");
    }

    @DeleteMapping("/api/user/like/comment/{id}")
    public ResponseDto<String> unlikeComment(@PathVariable Long id){
        likeService.unikeComment(id);

        return new ResponseDto<>(HttpStatus.OK, "댓글 좋아요 취소");
    }

    @PostMapping("/api/user/like/recomment/{id}")
    public ResponseDto<String> likeRecomment(@PathVariable Long id){
        likeService.likeRecomment(id);

        return new ResponseDto<>(HttpStatus.OK, "대댓글 좋아요 완료");
    }

    @DeleteMapping("/api/user/like/recomment/{id}")
    public ResponseDto<String> unlikeRecomment(@PathVariable Long id){
        likeService.unlikeRecomment(id);

        return new ResponseDto<>(HttpStatus.OK, "대댓글 좋아요 취소");
    }

}
