package com.example.todo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todo.model.tasks;
import com.example.todo.model.users;


public interface TaskRepo extends JpaRepository<tasks,Long>{
	
	List<tasks> findAllByUserId(long userid);
	
	boolean existsByUserAndTitle(users user, String title);
}
