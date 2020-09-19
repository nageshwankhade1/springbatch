package com.nmw.example.springbatch.repository;

import com.nmw.example.springbatch.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
