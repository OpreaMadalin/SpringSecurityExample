package com.project.model;

import com.project.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.PrePersist;

public class UserListener {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserListener(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @PrePersist
  public void userPrePersist(User user) {
    user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()));
  }
}
