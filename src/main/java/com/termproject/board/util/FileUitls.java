package com.termproject.board.util;

import java.util.UUID;

public class FileUitls {
//CommonUtils.buildFileName(category, multipartFile.getOriginalFilename());
    public static String buildFileName(String category, String filename){
        String randomId = UUID.randomUUID().toString();
        return category + filename + "/" + randomId;
    }
}
