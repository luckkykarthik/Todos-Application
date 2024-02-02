package com.example.todo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.model.users;

public interface UserRepo extends JpaRepository<users,Long>{
	
	Optional<users> findByEmail(String email);
}
