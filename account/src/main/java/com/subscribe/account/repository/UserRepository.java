package com.subscribe.account.repository;

import com.subscribe.account.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
