package com.batch.demo.infrastructure.batch;

import com.batch.demo.domain.model.User;
import com.batch.demo.domain.ports.UserRepositoryPort;
import com.batch.demo.infrastructure.batch.processor.UserItemProcessor;
import com.batch.demo.infrastructure.batch.reader.UserItemReader;
import com.batch.demo.infrastructure.batch.writer.UserItemWriter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@Configuration
public class UserBatchConfig {

    private final UserRepositoryPort userRepositoryPort;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final UserItemReader userItemReader;
    private final UserItemProcessor userItemProcessor;
    private final UserItemWriter userItemWriter;

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepository){
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        return jobLauncher;
    }


    @Bean
    public Job userJob(Step userStep) {
        return new JobBuilder("userJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(userStep)
                .build();
    }

    @Bean
    public Step userStep() {
        return new StepBuilder("userStep", jobRepository)
                .<User, User>chunk(10, transactionManager)
                .reader(userItemReader)
                .processor(userItemProcessor)
                .writer(userItemWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("batch-user");
    }

}
