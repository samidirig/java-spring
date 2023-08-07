package com.tutorial.springtutorial.repository;

import com.tutorial.springtutorial.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
