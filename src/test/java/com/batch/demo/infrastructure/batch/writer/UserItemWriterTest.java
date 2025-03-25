package com.batch.demo.infrastructure.batch.writer;

import com.batch.demo.domain.model.User;
import com.batch.demo.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class UserItemWriterTest {

    @MockitoBean
    private UserRepository userRepository;
    private UserItemWriter userItemWriter;

    @BeforeEach
    void setUp() {

        userItemWriter = new UserItemWriter(userRepository);

    }

    @Test
    void testWrite() throws Exception {
//        UserItemWriter userItemWriter = new UserItemWriter(userRepository);

        List<User> users = List.of(
                new User(1L, "TestUser1", "test1@example.com", true),
                new User(2L, "TestUser2", "test2@example.com", false)
        );

        // Act
        userItemWriter.write(new org.springframework.batch.item.Chunk<>(users));

        // Assert
        assertEquals(2, userRepository.count());
    }
}
