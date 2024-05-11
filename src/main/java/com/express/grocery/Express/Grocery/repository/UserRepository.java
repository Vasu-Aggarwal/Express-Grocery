package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{
    Boolean findByEmail(String email);
    Boolean findByMobile(Long mobile);
}
