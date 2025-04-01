package com.batch.demo.infrastructure.batch.reader;

import com.batch.demo.domain.model.User;
import com.batch.demo.domain.ports.UserRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Component
public class UserItemReader implements ItemReader<User> {

    private final UserRepositoryPort userRepositoryPort;
    private Iterator<User> userIterator;

    @Override
    public User read() {
        if (userIterator == null || !userIterator.hasNext()) {
            List<User> users = userRepositoryPort.findAll();
            userIterator = users.iterator();
        }
        return userIterator.hasNext() ? userIterator.next() : null;
    }
}

