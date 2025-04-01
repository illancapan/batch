package com.batch.demo.infrastructure.batch;

import com.batch.demo.domain.model.User;
import com.batch.demo.domain.ports.UserRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@RequiredArgsConstructor
@AllArgsConstructor
@Configuration
public class UserBatchConfig {

    private final UserRepositoryPort userRepositoryPort;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;


    @Bean
    public Job userJob(Step userStep) {
        return new JobBuilder("userJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(userStep)
                .build();
    }

    public Step userStep(ItemReader<User> reader, ItemProcessor<User> writer) {
        return new StepBuilder("userStep", jobRepository)
                .<User, User>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .taskExecutor(taskExecutor())
                .build();
    }
}


}
