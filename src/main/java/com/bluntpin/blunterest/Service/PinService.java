package com.bluntpin.blunterest.Service;

import com.bluntpin.blunterest.Model.Pin;
import com.bluntpin.blunterest.Repository.PinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PinService {

    private final PinRepository pinRepository;
    private final S3Client s3Client;

    @Value("${s3.bucket-name}")
    private String bucketName;

    @Value("${s3.storage-url}")
    private String storageURL;


    public List<Pin> getAllPins()
    {
        return pinRepository.findAll();
    }

    public void uploadPin(MultipartFile multipartFile, Pin pin) throws IOException {
        String filename = "files/" + UUID.randomUUID().toString().replace("-", "") + "_" + multipartFile.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(multipartFile.getBytes()));
        pin.setImageUrl(generateFileUrl(filename));

        pinRepository.save(pin);
    }

    private String generateFileUrl(String fileKey) {
        return String.format("https://%s.%s/%s",
                bucketName,
                storageURL.replace("https://", ""),
                fileKey);
    }

}
