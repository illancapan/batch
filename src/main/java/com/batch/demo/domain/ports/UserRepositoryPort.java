package com.batch.demo.domain.ports;

import com.batch.demo.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void saveAll(List<User> users);

}
