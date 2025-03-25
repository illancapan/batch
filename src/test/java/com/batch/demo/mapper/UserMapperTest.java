package com.batch.demo.mapper;

import com.batch.demo.application.mapper.UserMapper;
import com.batch.demo.dto.UserDTO;
import com.batch.demo.domain.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserMapperTest {


    @Test
    void testToDTO() {
        User user = new User();
        user.setId(1L);
        user.setName("Juan Doe");
        user.setEmail("juan@doe.com");
        user.setActive(true);

        UserDTO userDTO = UserMapper.toDTO(user);

        assertNotNull(userDTO);
        assertEquals(1L, userDTO.getId());
        assertEquals("Juan Doe", userDTO.getName());
        assertEquals("juan@doe.com", userDTO.getEmail());
        assertTrue(userDTO.isActive());

    }

    @Test
    void testToEntity() {

// LO PASO POR PATRON BUILDER

        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .name("Juan Doe")
                .email("juan.doe@example.com")
                .active(true)
                .build();

        User user = UserMapper.toEntity(userDTO);

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("Juan Doe", user.getName());
        assertEquals("juan.doe@example.com", user.getEmail());
        assertTrue(user.isActive());

    }
}