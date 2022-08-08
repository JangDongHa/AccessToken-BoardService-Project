package com.termproject.board.like.test;

import java.util.HashMap;
import java.util.Map;

public class MemoryPostTestRepository implements PostTestRepository {

    private static Map<Long,PostTest> test = new HashMap<>();

    @Override
    public void save(PostTest postTest) {
        test.put(postTest.getId(),postTest);

    }
    @Override
    public PostTest findById(Long postTestId){return test.get(postTestId); }



}
