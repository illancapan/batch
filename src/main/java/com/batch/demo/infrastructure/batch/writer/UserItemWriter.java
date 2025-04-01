package com.batch.demo.infrastructure.batch.writer;

import com.batch.demo.domain.model.User;
import com.batch.demo.domain.ports.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserItemWriter implements ItemWriter<User> {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void write(Chunk<? extends User> chunk) throws Exception {
        List<User> userList = new ArrayList<>(chunk.getItems());
        userRepositoryPort.saveAll(userList);
    }
}
