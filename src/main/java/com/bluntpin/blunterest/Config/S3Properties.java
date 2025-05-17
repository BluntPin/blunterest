package com.bluntpin.blunterest.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "s3")
public class S3Properties {
    private String accessKey;
    private String secretKey;
    private String region;
    private String storageUrl;
    private String bucketName;
}
