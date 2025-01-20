package com.example.Hari.NewSpringBatch.Controller;


import com.example.Hari.NewSpringBatch.Config.SpringBatchConfig;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.generic.LOOKUPSWITCH;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j



@RestController
@RequestMapping("upload")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;


    @Autowired
    private SpringBatchConfig springBatchConfig;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private Step steps;


//    @Autowired
//    private BatchUpload batchUpload;
//

    @PostMapping("/importJobs")
    private String jobLauncher() {
        JobParameters jobParameters = new JobParametersBuilder().
                addLong("StartAt", System.currentTimeMillis())
                .toJobParameters();
        try {
            // Launch the job
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            // return the job Status
            return jobExecution.getStatus().toString();
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            return "Job failed with exceptions" + e.getMessage();
        }
    }


    @PostMapping("/custom/files")
    private String jobLaunchers(
            @RequestParam("csvFile") MultipartFile csvFile,
            @RequestParam("jsonFile") MultipartFile jsonFile) throws IOException {

//        String filePath = "/home/hariharasudhan/Documents/SpringBatch/people-1000.csv";
//        String jsonPath = "/home/hariharasudhan/Documents/SpringBatch/people-1000.json";

//        String filePath = new ClassPathResource("people-1000.csv").getPath();
//        String jsonPath = new ClassPathResource("people-1000.json").getPath();

        File tempFile = saveMultipartFile(csvFile);
        File tempJsonFile = saveMultipartFile(jsonFile);

        String tempFilePath = tempFile.getAbsolutePath();
        String tempJsonPath = tempJsonFile.getAbsolutePath();

        String fileName = getFileNameFromFilePath(tempFilePath);

        Job newJob = springBatchConfig.createJob(jobRepository, steps);

        JobParameters jobParam = new JobParametersBuilder()
                .addString("name", "hari")
                .addString("filePath", tempFilePath)
                .addString("jsonPath", tempJsonPath)
                .addString("fileName", fileName)
                .toJobParameters();

        try {
            final JobExecution jobExecution = jobLauncher.run(newJob, jobParam);
            return jobExecution.getStatus().toString();
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
                 | JobParametersInvalidException e) {
            return "Job failed with exception : " + e.getMessage();
        }
    }

    private String getFileNameFromFilePath(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }

    private File saveMultipartFile(MultipartFile multipartFile) throws IOException, IOException {
        File tempFile = File.createTempFile("uploaded-", "-" + multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempFile);
        tempFile.deleteOnExit(); // Automatically delete the file when the JVM exits
        return tempFile;
    }


}



