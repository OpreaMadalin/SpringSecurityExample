package com.project.repository;

import com.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findByUsername(String username);

  User findByEmail(String email);

  List<User> findAllByUsername(String username);
}
