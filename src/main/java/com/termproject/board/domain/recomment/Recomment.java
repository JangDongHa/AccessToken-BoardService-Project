package com.termproject.board.domain.recomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.termproject.board.domain.comment.Comment;
import com.termproject.board.domain.common.BaseTimeEntity;
import com.termproject.board.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Recomment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    private int likes;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "comment_id")
    private Comment comment;

}
