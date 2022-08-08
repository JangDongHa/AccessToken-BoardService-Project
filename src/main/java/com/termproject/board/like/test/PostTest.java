package com.termproject.board.like.test;


import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@NoArgsConstructor
public class PostTest {
    @Id
    private Long id;
    private Long likes;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getId() {
        return id;
    }

    public PostTest(Long id, Long likes) {
        this.id = id;
        this.likes = likes;
    }

}