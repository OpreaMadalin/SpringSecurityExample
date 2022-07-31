package com.project.controller;

import com.project.dto.user.UserDto;
import com.project.service.UserService;
import com.project.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;
  private final UserMapper userMapper;

  @Autowired
  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
    this.userMapper = userMapper;
  }

  @GetMapping
  public List<UserDto> findAll() {
    return userMapper.toDtos(userService.findAll());
  }

  @GetMapping("/username/{username}")
  public List<UserDto> findAllByUsername(@PathVariable("username") String username) {
    return userMapper.toDtos(userService.findAllByUsername(username));
  }
}
