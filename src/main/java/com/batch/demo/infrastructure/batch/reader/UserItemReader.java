package com.batch.demo.infrastructure.batch.reader;

import com.batch.demo.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class UserItemReader implements ItemReader<UserDTO> {

    private final Iterator<UserDTO> userIterator;

    @Override
    public UserDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return userIterator.hasNext() ? userIterator.next() : null;
    }
}
