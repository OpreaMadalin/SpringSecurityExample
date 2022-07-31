package com.project.service.mapper;

import com.project.dto.user.UserDto;
import com.project.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User fromDto(UserDto userDto);

  UserDto toDto(User byUsername);

  List<UserDto> toDtos(List<User> all);
}
