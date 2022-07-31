package com.project.controller;

import com.project.dto.user.UserDto;
import com.project.exceptionHandler.customException.BadCredentialException;
import com.project.security.jwtToken.JwtUtil;
import com.project.service.AuthUserService;
import com.project.service.UserService;
import com.project.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final JwtUtil jwtUtil;
  private final UserMapper userMapper;
  private final UserService userService;
  private final AuthUserService authUserService;
  private final AuthenticationManager authenticationManager;

  @Autowired
  public AuthController(
      JwtUtil jwtUtil,
      UserMapper userMapper,
      UserService userService,
      AuthUserService authUserService,
      AuthenticationManager authenticationManager) {
    this.jwtUtil = jwtUtil;
    this.userMapper = userMapper;
    this.userService = userService;
    this.authUserService = authUserService;
    this.authenticationManager = authenticationManager;
  }

  @PostMapping(value = "/login")
  public ResponseEntity<String> authenticate(@RequestBody @Valid UserDto userDto) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
    } catch (Exception ex) {
      throw new BadCredentialException("Incorrect credentials", ex);
    }
    UserDetails userDetails = authUserService.loadUserByUsername(userDto.getUsername());
    String token = jwtUtil.generateToken(userDetails);

    return ResponseEntity.ok("Token: " + token);
  }

  @PostMapping(value = "/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void register(@RequestBody @Valid UserDto userDto) {
    userService.save(userMapper.fromDto(userDto));
  }
}
