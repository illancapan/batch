package com.batch.demo.infrastructure.batch.reader;

import com.batch.demo.dto.UserDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserItemReaderTest {


    @Test
    void testUserItemReader() throws Exception {

//        Arrange

        List<UserDTO> users = List.of(

                UserDTO.builder()
                        .id(1L)
                        .name("TestUser1")
                        .email("Test1@example.com")
                        .active(true)
                        .build(),

                UserDTO.builder()
                        .id(2L)
                        .name("TestUser2")
                        .email("Test2@example.com")
                        .active(false)
                        .build()

        );


        UserItemReader reader = new UserItemReader(users.iterator());

        UserDTO firstUser = reader.read();
        UserDTO secondUser = reader.read();
        UserDTO noUser = reader.read();

        assertEquals("TestUser1", firstUser.getName());
        assertEquals("TestUser2", secondUser.getName());
        assertNull(noUser);

    }
}