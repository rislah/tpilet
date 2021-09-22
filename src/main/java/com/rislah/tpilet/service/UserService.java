package com.rislah.tpilet.service;

import com.rislah.tpilet.dto.UserDto;
import com.rislah.tpilet.exception.UserExistsException;
import com.rislah.tpilet.mapper.UserMapper;
import com.rislah.tpilet.model.User;
import com.rislah.tpilet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository repository;
    private UserMapper userMapper;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void create(UserDto userDto) {
        if (repository.existsByUsername(userDto.getUsername())) {
            throw new UserExistsException();
        }
        User user = userMapper.userDtoToUser(userDto);
        repository.saveAndFlush(user);
    }
}
