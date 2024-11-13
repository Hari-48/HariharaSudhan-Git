package com.example.Hari.Batchprocessing;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing

@SpringBootApplication
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})


public class BatchProcessingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchProcessingApplication.class, args);
    }
}
