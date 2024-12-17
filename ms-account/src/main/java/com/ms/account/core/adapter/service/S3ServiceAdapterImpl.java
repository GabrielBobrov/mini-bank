package com.ms.account.core.adapter.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.account.core.ports.in.service.log.ILogServicePort;
import com.ms.account.infrastructure.aws.s3.configuration.S3Properties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3ServiceAdapterImpl implements ILogServicePort {

    private final AmazonS3 amazonS3;
    private final S3Properties s3Properties;

    public void log(String path, Object entity) {
        ObjectMapper objectMapper = new ObjectMapper();
        String entityString;
        try {
            entityString = objectMapper.writeValueAsString(entity);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert entity to JSON", e);
        }

        File file = new File("/tmp/" + path);
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(entityString.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        amazonS3.putObject(new PutObjectRequest(s3Properties.getBucket(), path, file));
        log.info("Entity saved to S3");
        file.delete();
    }
}
