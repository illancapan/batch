package com.batch.demo.infrastructure.batch.processor;

import com.batch.demo.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
public class UserItemProcessor implements ItemProcessor<User, User> {



    @Override
    public User process(User user) {
        if (!user.isActive()){
            return null;
        }
        user.setName(user.getName().toUpperCase());
        return user;
    }
}
