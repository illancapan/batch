package com.batch.demo.application.mapper;

import com.batch.demo.dto.UserDTO;
import com.batch.demo.domain.model.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .active(user.isActive()).
                build();
    }

    public static User toEntity(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setActive(userDTO.isActive());
        return user;
    }
}
