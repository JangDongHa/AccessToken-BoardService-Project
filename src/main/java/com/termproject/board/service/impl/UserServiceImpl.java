package com.termproject.board.service.impl;


import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.board.BoardLike;
import com.termproject.board.domain.board.BoardLikeRepository;
import com.termproject.board.domain.board.BoardRepository;
import com.termproject.board.domain.comment.Comment;
import com.termproject.board.domain.comment.CommentLike;
import com.termproject.board.domain.comment.CommentLikeRepository;
import com.termproject.board.domain.comment.CommentRepository;
import com.termproject.board.domain.recomment.Recomment;
import com.termproject.board.domain.recomment.RecommentLike;
import com.termproject.board.domain.recomment.RecommentLikeRepository;
import com.termproject.board.domain.recomment.RecommentRepository;
import com.termproject.board.domain.user.User;
import com.termproject.board.domain.user.UserRepository;
import com.termproject.board.dto.*;
import com.termproject.board.exception.ExceptionNamingHandler;
import com.termproject.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardLikeRepository boardLikeRepository;

    @Autowired
    private CommentLikeRepository commentLikeRepository;

    @Autowired
    private RecommentRepository recommentRepository;

    @Autowired
    private RecommentLikeRepository recommentLikeRepository;



    @Transactional
    @Override
    public ResponseDto<String> joinUser(RequestUserDto requestUserDto){
        User user = requestUserDto.toUser();
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new IllegalArgumentException(ExceptionNamingHandler.NICKNAME_OVERLAP_ERROR);


        if (checkUsernameAndPassword(user.getUsername(), user.getPassword())){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);

            return new ResponseDto<>(HttpStatus.OK, "???????????? ??????");
        }

        return new ResponseDto<>(HttpStatus.OK, "???????????? ?????? (????????? ?????? ???????????? ????????? ?????????????????????.)");
    }

    private boolean checkUsernameAndPassword(String username, String password){
        if (!(username.length() >= 4 && username.length() <= 12 && !findStr("[^a-zA-Z0-9]", username))) // a~z, A~Z, 0~9 ????????? ????????? ???????????? false??? ??????
            throw new IllegalArgumentException(ExceptionNamingHandler.USERNAME_ERROR);

        if (!(password.length() >= 4 && password.length() <= 32 && !findStr("[^a-z0-9]", password))) // a~z, 0~9 ????????? ????????? ???????????? false??? ??????
            throw new IllegalArgumentException(ExceptionNamingHandler.PASSWORD_ERROR);

        return true;
    }

    private boolean findStr(String regex, String str){
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(str);
        return m.find();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseBoardDto> getAllBoardsByUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        List<Board> boards = userRepository.findById(user.getId()).orElseThrow(()->new IllegalArgumentException("Can not find boards")).getBoards();
        List<ResponseBoardDto> responseBoards = new ArrayList<>();
        boards.forEach(board -> responseBoards.add(new ResponseBoardDto(board)));

        return responseBoards;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseBoardDto> getAllLikeBoardsByUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        List<BoardLike> boardlikes = boardLikeRepository.findAllByUser(user).orElseThrow(()->new IllegalArgumentException("Can not find like boards by user"));
        List<Board> boards = new ArrayList<>();
        boardlikes.forEach(boardLike -> boards.add(boardLike.getBoard()));
        List<ResponseBoardDto> responseBoards = new ArrayList<>();
        boards.forEach(board -> responseBoards.add(new ResponseBoardDto(board)));

        return responseBoards;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseCommentDto> getAllCommentsByUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        List<Board> boards = userRepository.findById(user.getId()).orElseThrow(()->new IllegalArgumentException("Can not find boards")).getBoards();
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < boards.size(); i++) {
            comments.addAll(boards.get(i).getComments());
        }
        List<ResponseCommentDto> responseComments = new ArrayList<>();
        comments.forEach(comment -> responseComments.add(new ResponseCommentDto(comment)));

        return responseComments;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseCommentDto> getAllLikeCommentsByUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        List<CommentLike> commentLikes = commentLikeRepository.findAllByUser(user).orElseThrow(()->new IllegalArgumentException("Can not find like comments by user"));
        List<Comment> comments = new ArrayList<>();
        commentLikes.forEach(commentLike -> comments.add(commentLike.getComment()));
        List<ResponseCommentDto> responseComments = new ArrayList<>();
        comments.forEach(comment -> responseComments.add(new ResponseCommentDto(comment)));

        return responseComments;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseRecommentDto> getAllRecommentByUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        List<Recomment> recomments = recommentRepository.findAllByUser(user).orElseThrow(()->new IllegalArgumentException("Can not find recomments by user"));
        List<ResponseRecommentDto> responseRecomments = new ArrayList<>();

        recomments.forEach(recomment -> responseRecomments.add(new ResponseRecommentDto(recomment)));
        return responseRecomments;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseRecommentDto> getAllLikeRecommentByUser(String username){
        User user = userRepository.findByUsername(username).orElseThrow(()->new IllegalArgumentException(ExceptionNamingHandler.CANNOT_FIND_USERNAME));
        List<RecommentLike> recommentLikes = recommentLikeRepository.findAllByUser(user).orElseThrow(()->new IllegalArgumentException("Can not find like recomments by user"));
        List<Recomment> recomments = new ArrayList<>();
        recommentLikes.forEach(recommentLike -> recomments.add(recommentLike.getRecomment()));
        List<ResponseRecommentDto> responseRecomments = new ArrayList<>();
        recomments.forEach(recomment -> responseRecomments.add(new ResponseRecommentDto(recomment)));

        return responseRecomments;
    }







}
