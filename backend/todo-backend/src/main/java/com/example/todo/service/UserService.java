package com.example.todo.service;

import com.example.todo.payload.userDto;


public interface UserService {
	public userDto createUser(userDto userdto);
	
	public userDto getUserByUsername(String username);
}
