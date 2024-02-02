package com.example.todo.serviceImp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todo.exception.UserNotFound;
import com.example.todo.exception.emailAlreadyExists;
import com.example.todo.model.users;
import com.example.todo.payload.userDto;
import com.example.todo.repositories.UserRepo;
import com.example.todo.service.UserService;




@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;  

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public userDto createUser(userDto userdto) {
		try {
    		userdto.setPassword(passwordEncoder.encode(userdto.getPassword()));
            users user=modelMapper.map(userdto,users.class);
            users saveduser=userRepo.save(user);
            return modelMapper.map(saveduser,userDto.class);
    	}
    	catch(Exception e) {
    		throw new emailAlreadyExists("Email already Exists");
    	}
	}

	@Override
	public userDto getUserByUsername(String username) {
		users user =userRepo.findByEmail(username).orElseThrow(
				() -> new UserNotFound(String.format("User %s not found", username))
				);
		return modelMapper.map(user,userDto.class);
	}

}
