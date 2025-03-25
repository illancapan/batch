package com.batch.demo.config;

import com.batch.demo.application.mapper.UserMapper;
import com.batch.demo.domain.model.User;
import com.batch.demo.domain.repository.UserRepository;
import com.batch.demo.dto.UserDTO;
import com.batch.demo.infrastructure.batch.processor.UserItemProcessor;
import com.batch.demo.infrastructure.batch.reader.UserItemReader;
import com.batch.demo.infrastructure.batch.writer.UserItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Iterator;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public Job userJob(JobRepository jobRepository, Step userStep) {
        return new JobBuilder("userJob", jobRepository)
                .start(userStep)
                .build();
    }

    @Bean
    public Step userStep(JobRepository jobRepository,
                         PlatformTransactionManager transactionManager,
                         UserItemReader userItemReader,
                         UserItemProcessor userItemProcessor,
                         UserItemWriter userItemWriter) {

        return new StepBuilder("userStep", jobRepository)
                .<UserDTO, User>chunk(10, transactionManager)
                .reader(userItemReader)
                .processor(userItemProcessor)
                .writer(userItemWriter)
                .build();
    }

    @Bean
    public Iterator<UserDTO> userDTOIterator(UserRepository userRepository) {
        List<UserDTO> userDTOList = userRepository.findAll().stream()
                .map(UserMapper::toDTO)
                .toList();
        return userDTOList.iterator();
    }


}
