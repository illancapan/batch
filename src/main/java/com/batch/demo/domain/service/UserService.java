package com.batch.demo.domain.service;

import com.batch.demo.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getActiveUsers();

    void processUsers(List<UserDTO> userDTOs);

}
