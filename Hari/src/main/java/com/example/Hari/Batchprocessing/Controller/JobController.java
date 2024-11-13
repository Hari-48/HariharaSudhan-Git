package com.example.Hari.Batchprocessing.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/jobs")

public class JobController {

    private static final Logger log= LoggerFactory.getLogger(JobController.class);
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @PostMapping("/importCustomers")
    public void importCsvToDBJob() {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("StartAt", System.currentTimeMillis()).toJobParameters();

//       log.info("JOB PARAM:{}",jobParameters.getParameters());
//       log.info("JOb:{}",job);
        try {
//            log.info("Inside Try");
            jobLauncher.run(job,jobParameters);
//            log.info("Outside Try");
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}



