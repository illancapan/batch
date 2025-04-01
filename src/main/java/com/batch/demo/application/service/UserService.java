package com.batch.demo.application.service;

import com.batch.demo.domain.model.User;
import com.batch.demo.domain.ports.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepositoryPort userRepositoryPort;

    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }

}
