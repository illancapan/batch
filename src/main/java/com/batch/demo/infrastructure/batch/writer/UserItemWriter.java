package com.batch.demo.infrastructure.batch.writer;

import com.batch.demo.domain.model.User;
import com.batch.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserItemWriter implements ItemWriter<User> {

    private final UserRepository userRepository;


    @Override
    public void write(Chunk<? extends User> chunk) throws Exception {
        try {
            for (User user : chunk) {
                userRepository.saveAll(chunk);
            }
        } catch (Exception e) {
            System.out.println("Error al escribir los usuarios " + e.getMessage());
            throw e;
        }

    }
}
