package org.gordeser.scanner.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@Log4j2
public class S3Service {
    private final AmazonS3 s3client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Autowired
    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public String uploadFile(String keyName, MultipartFile file) throws IOException {
        String sha256HexKeyName = Hashing.sha256().hashString(keyName, StandardCharsets.UTF_8).toString();

        PutObjectResult putObjectResult = s3client.putObject(
                bucketName,
                sha256HexKeyName + "." + Files.getFileExtension(keyName),
                file.getInputStream(),
                null
        );

        log.info(putObjectResult.getMetadata().toString());
        return sha256HexKeyName;
    }

    public S3Object getFile(String keyName) {
        return s3client.getObject(bucketName, keyName);
    }
}
