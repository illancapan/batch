package com.batch.demo.application.service;

import com.batch.demo.domain.model.User;
import com.batch.demo.domain.ports.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepositoryPort userRepositoryPort;

    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }

}
