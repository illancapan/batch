package com.batch.demo.mapper;

import com.batch.demo.dto.UserDTO;
import com.batch.demo.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
                .name("John Doe")
                .email("john.doe@example.com")
                .active(true)
                .build();

        User user = UserMapper.toEntity(userDTO);

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
        assertTrue(user.isActive());

    }
}