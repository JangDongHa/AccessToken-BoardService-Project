package com.termproject.board.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AwsS3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;


//    @Transactional
//    public List<String> uploadFile(User user, Notice notice, List<MultipartFile> multipartFile) {
//
//        List<String> fileNameList = new ArrayList<>();
//
//        multipartFile.forEach(file -> {
//            String fileName = createFileName(file.getOriginalFilename());
//            ObjectMetadata objectMetadata = new ObjectMetadata();
//            objectMetadata.setContentLength(file.getSize());
//            objectMetadata.setContentType(file.getContentType());
//
//            try(InputStream inputStream = file.getInputStream()) {
//                amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
//                        .withCannedAcl(CannedAccessControlList.PublicRead));
//            } catch(IOException e) {
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
//            }
//            // 원본파일이름과 변경된 파일이름을 DB에 저장 -> 다운로드시 필요
//            NoticeFile noticefile = NoticeFile.builder()
//                    .newFileName(fileName)
//                    .originFileName(file.getOriginalFilename())
//                    .user(user)
//                    .notice(notice)
//                    .build();
//
//            noticeFileRepository.save(noticefile);
//
//            fileNameList.add(fileName);
//        });
//        return fileNameList;
//    }


}
