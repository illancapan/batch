package com.batch.demo.infrastructure.repository;

import com.batch.demo.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
