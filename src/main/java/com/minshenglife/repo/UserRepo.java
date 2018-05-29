package com.minshenglife.repo;

import com.minshenglife.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findByUsername(String username);
}
