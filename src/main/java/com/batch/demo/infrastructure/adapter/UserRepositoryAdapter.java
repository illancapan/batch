package com.batch.demo.infrastructure.adapter;

import com.batch.demo.domain.model.User;
import com.batch.demo.domain.ports.UserRepositoryPort;
import com.batch.demo.infrastructure.entity.UserEntity;
import com.batch.demo.infrastructure.repository.UserJpaRepository;
import com.batch.demo.infrastructure.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        UserEntity savedEntity = userJpaRepository.save(entity);
        return UserMapper.toDomain(savedEntity);
    }

    @Override
    public void saveAll(List<User> users) {
        List<UserEntity> entities = users.stream()
                .map(UserMapper::toEntity)
                .collect(Collectors.toList());
        userJpaRepository.saveAll(entities);
    }
}
