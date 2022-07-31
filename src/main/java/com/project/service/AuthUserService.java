package com.project.service;

import com.project.exceptionHandler.customException.EmailNotFoundException;
import com.project.exceptionHandler.customException.UsernameNotFoundException;
import com.project.model.CustomUserDetails;
import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public AuthUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User with username -> " + username + " not found.");
    }
    return new CustomUserDetails(user);
  }

  public UserDetails loadUserByEmail(String email) {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new EmailNotFoundException("User with email -> " + email + " not found.");
    }
    return new CustomUserDetails(user);
  }
}
