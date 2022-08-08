package com.termproject.board.like.test;

public class PostTestServiceImpl implements PostTestService{
    private final PostTestRepository postTestRepository = new MemoryPostTestRepository();

    @Override
    public void post(PostTest postTest){
        postTestRepository.save(postTest);
    }

    @Override
    public PostTest findById(Long postTestId){
        return postTestRepository.findById(postTestId);
    }

}
