package com.example.springBootJPA.Controller;


import com.example.springBootJPA.Entity.Person;
import com.example.springBootJPA.Model.BatchUpload;
import com.example.springBootJPA.config.SpringBatchConfig;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

@RestController
@RequestMapping("spring-batch/jobs")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private SpringBatchConfig springBatchConfig;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private Step step;


    @Autowired
    private Job job;

    @Autowired
    private BatchUpload batchUpload;

    @PostMapping("/importData")
    public String jobLauncher()  {

        String filePath = "/home/hariharasudhan/Documents/SpringBatch/people-1000.csv";
        String jsonPath = "/home/hariharasudhan/Documents/SpringBatch/people-1000.json";


        String fileName = getFileNameFromFilePath(filePath);
        Job job = batchUpload.createJob(fileName,jobRepository, step);




        JobParameters jobParam = new JobParametersBuilder()
                .addString("name", "hari")
                .addString("filePath", filePath)
                .addString("jsonPath", jsonPath)
                .addString("fileName", fileName)
                .toJobParameters();

        try {
            final JobExecution jobExecution = jobLauncher.run(job, jobParam);
            return jobExecution.getStatus().toString();
        }catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                | JobParametersInvalidException e){
            return "Job failed with exception : " + e.getMessage();
        }





/*        final JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            // Launch the job
            final JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        // Return job status
        return jobExecution.getStatus().toString();
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException e) {
            e.printStackTrace();
            return "Job failed with exception: " + e.getMessage();
        }*/



    }
    private String getFileNameFromFilePath(String filePath) {
        File file = new File(filePath);
        return file.getName(); // Returns just the filename, e.g., "example.txt"
    }
}



