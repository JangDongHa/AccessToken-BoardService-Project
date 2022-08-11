package com.termproject.board.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.termproject.board.domain.board.Board;
import com.termproject.board.domain.board.BoardRepository;
import com.termproject.board.exception.ExceptionNamingHandler;
import com.termproject.board.util.FileUitls;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@Service
public class AwsS3ServiceImpl {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;

    private final BoardRepository boardRepository;

    @Transactional
    public String uploadFileV1(MultipartFile multipartFile, String username) throws IOException {

        String category = "termproject-image/";
        validateFileExists(multipartFile);

        String fileName = FileUitls.buildFileName(category, multipartFile.getOriginalFilename());

        // AWS SDK 객체
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());

        // 실제 S3 에 적용시키는 구간
        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new IOException(ExceptionNamingHandler.FAILED_SAVED);
        }


        String filePath = amazonS3Client.getUrl(bucket, fileName).toString();

        return filePath;
    }

    private void validateFileExists(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            throw new IOException(ExceptionNamingHandler.FAILED_SAVED);
        }
    }


}
