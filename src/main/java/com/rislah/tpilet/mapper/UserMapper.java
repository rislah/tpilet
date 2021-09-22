package com.rislah.tpilet.mapper;

import com.rislah.tpilet.dto.UserDto;
import com.rislah.tpilet.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserDto userDto);
}
