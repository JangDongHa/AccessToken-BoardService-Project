package com.termproject.board.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.common.BaseTimeEntity;
import com.termproject.board.domain.recomment.Recomment;
import com.termproject.board.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "board_id")
    private Board board;

    private int likes;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "comment", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"comment"})
    @OrderBy("id desc")
    private List<Recomment> recomments;

}
