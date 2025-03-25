package com.batch.demo.infrastructure.batch.processor;

import com.batch.demo.domain.model.User;
import com.batch.demo.dto.UserDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserItemProcessorTest {

    @Test
    void testProcessorValidItem() throws Exception {

        UserDTO input = UserDTO.builder()
                .id(1L)
                .name("test user")
                .email("test@example.com")
                .active(true)
                .build();

        UserItemProcessor processor = new UserItemProcessor();
        User result = processor.process(input);

        assertEquals(1L, result.getId());
        assertEquals("test user", result.getName());
        assertTrue(result.isActive());
    }
}