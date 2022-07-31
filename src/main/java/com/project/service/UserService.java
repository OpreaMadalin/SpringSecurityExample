package com.project.service;

import com.project.exceptionHandler.customException.EmailExistException;
import com.project.exceptionHandler.customException.ModelConflictException;
import com.project.exceptionHandler.customException.UsernameExistException;
import com.project.exceptionHandler.customException.UsernameNotFoundException;
import com.project.model.User;
import com.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void save(User user) {
    validateSave(user);
    try {
      userRepository.save(user);
    } catch (Exception ex) {
      throw new ModelConflictException("Save User Exception.", ex);
    }
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public List<User> findAllByUsername(String username) {
    List<User> users = userRepository.findAllByUsername(username);
    if (users.isEmpty()) {
      throw new UsernameNotFoundException("Username -> " + username + " not exist in DB");
    }

    return users;
  }

  private void validateSave(User user) {
    if (userRepository.findByUsername(user.getUsername()) != null) {
      throw new UsernameExistException("Username -> " + user.getUsername() + " already exist.");
    }
    if (userRepository.findByEmail(user.getEmail()) != null) {
      throw new EmailExistException("User email -> " + user.getEmail() + " already exist.");
    }
  }
}
