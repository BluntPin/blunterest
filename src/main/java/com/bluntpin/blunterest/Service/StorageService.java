package com.bluntpin.blunterest.Service;

import com.bluntpin.blunterest.Config.S3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URLConnection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final S3Properties s3Properties;
    private final S3Client s3Client;


    public String uploadFile(MultipartFile file) {
        String filename = "files/" + UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();

        String contentType = file.getContentType();
        if (contentType == null) {
            contentType = URLConnection.guessContentTypeFromName(file.getOriginalFilename());
        }

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(s3Properties.getBucketName())
                .key(filename)
                .contentType(contentType)
                .contentDisposition("inline")
                .build();

        try {
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return generateFileUrl(filename);
    }

    public void deleteFileByUrl(String fileUrl) {
        s3Client.deleteObject(b -> b
                .bucket(s3Properties.getBucketName())
                .key(fileUrl));
    }


    private String generateFileUrl(String filename) {
        return String.format("%s/%s/%s", s3Properties.getStorageUrl(), s3Properties.getBucketName(), filename);
    }


}
