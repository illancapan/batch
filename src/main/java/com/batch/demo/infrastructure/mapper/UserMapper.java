package com.batch.demo.infrastructure.mapper;

import com.batch.demo.domain.model.User;
import com.batch.demo.infrastructure.entity.UserEntity;
import org.hibernate.query.UnknownSqlResultSetMappingException;

public class UserMapper {


    public static User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .active(entity.isActive())
                .build();
    }

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .active(user.isActive())
                .build();
    }
}
