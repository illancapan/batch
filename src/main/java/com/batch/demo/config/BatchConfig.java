package com.batch.demo.config;

import com.batch.demo.domain.model.User;
import com.batch.demo.dto.UserDTO;
import com.batch.demo.infrastructure.batch.writer.UserItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job userJob(Step userStep) {
        return new org.springframework.batch.core.job.SimpleJob("userJob", userStep);
    }

    @Bean
    public Step userStep(StepBuilderFactory stepBuilderFactory,
                         UserItemReader userItemReader,
                         UserItemProcessor userItemProcessor,
                         UserItemWriter userItemWriter) {

        return stepBuilderFactory.get("userStep")
                .<UserDTO, User>chunk(10)
                .reader(userItemReader)
                .processor(userItemProcessor)
                .writer(userItemWriter)

                .build();
    }
}
