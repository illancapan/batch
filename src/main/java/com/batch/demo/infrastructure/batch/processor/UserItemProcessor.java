package com.batch.demo.infrastructure.batch.processor;

import com.batch.demo.domain.model.User;
import com.batch.demo.dto.UserDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
public class UserItemProcessor implements ItemProcessor<UserDTO, User> {
    @Override
    public User process(UserDTO item) throws Exception {

        if (item == null) {
            return null;
        }
        return User.builder()
                .id(item.getId())
                .name(item.getName().toLowerCase())
                .email(item.getEmail())
                .active(item.isActive())
                .build();
    }
}
