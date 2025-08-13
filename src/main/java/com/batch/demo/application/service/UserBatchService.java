package com.batch.demo.application.service;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserBatchService {

    private final JobLauncher jobLauncher;
    private final Job userJob;

    public void runBatch() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(userJob, jobParameters);
    }
}
