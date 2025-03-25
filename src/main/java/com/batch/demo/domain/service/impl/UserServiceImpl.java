package com.batch.demo.domain.service.impl;

import com.batch.demo.application.mapper.UserMapper;
import com.batch.demo.domain.model.User;
import com.batch.demo.domain.repository.UserRepository;
import com.batch.demo.domain.service.UserService;
import com.batch.demo.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getActiveUsers() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void processUsers(List<UserDTO> userDTOs) {

        List<User> users = userDTOs.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());

        userRepository.saveAll(users);
    }
}
