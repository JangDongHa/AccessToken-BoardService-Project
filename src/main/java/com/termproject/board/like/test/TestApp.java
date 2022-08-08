package com.termproject.board.like.test;

public class TestApp {
    public static void main(String[] args) {
        PostTestService postTestService = new PostTestServiceImpl();
        PostTest postTest = new PostTest(1L,0L);
        postTestService.post(postTest);

        PostTest findId = postTestService.findById(1L);
        System.out.println("id 1번 = " + findId.getLikes());


    }
}
