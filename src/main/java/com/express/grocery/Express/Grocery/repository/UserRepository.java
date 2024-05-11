package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);
}
