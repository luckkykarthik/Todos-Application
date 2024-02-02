package com.example.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.payload.taskDto;
import com.example.todo.payload.userDto;
import com.example.todo.service.TaskService;
import com.example.todo.service.UserService;



@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TaskController {

	@Autowired
	private TaskService taskservice;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/{userid}/tasks")
	public ResponseEntity<taskDto>  saveTask(@PathVariable(name="userid") long userid,@RequestBody taskDto taskdto,
			Authentication authentication){
		String email = authentication.getName();
        userDto user = userService.getUserByUsername(email);
        long id=user.getId();
		return new ResponseEntity<>(taskservice.createTask(userid,taskdto,id),HttpStatus.CREATED);
	}
	
	@GetMapping("/{userid}/tasks")
	public ResponseEntity<List<taskDto>>  getAllTasks(@PathVariable(name="userid") long userid){
		return new ResponseEntity<>(taskservice.getAllTasks(userid),HttpStatus.OK);
	}
	
	@GetMapping("/{userid}/tasks/{id}")
	public ResponseEntity<taskDto> getTask(@PathVariable(name="userid") long userid,
													@PathVariable(name="id") long id){
		return new ResponseEntity<>(taskservice.getTask(userid,id),HttpStatus.OK);
	}
	@PutMapping("/{userid}/tasks/{id}")
	public ResponseEntity<taskDto> updateTask(@PathVariable(name="userid") long userid,@RequestBody taskDto taskdto,
					@PathVariable(name="id") long id){
		return new ResponseEntity<>(taskservice.updateTask(userid,id,taskdto),HttpStatus.OK);
	}
	@DeleteMapping("/{userid}/tasks/{id}")
	public ResponseEntity<String> DeleteTask(@PathVariable(name="userid") long userid,
			@PathVariable(name="id") long id){
		taskservice.deleteTask(userid,id);
		return new ResponseEntity<>("Task Deleted Successfully",HttpStatus.OK);
}
}
