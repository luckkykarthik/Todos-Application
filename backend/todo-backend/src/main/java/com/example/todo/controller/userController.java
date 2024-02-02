package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.exception.APIException;
import com.example.todo.payload.JwtAuthResponse;
import com.example.todo.payload.loginDto;
import com.example.todo.payload.userDto;
import com.example.todo.securityConfig.JwtTokenGenerator;
import com.example.todo.service.UserService;





@RestController
@RequestMapping("/todo")
@CrossOrigin("*")
public class userController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private JwtTokenGenerator jwtT;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@GetMapping("")
//	public String gethome() {
//		return "home";
//	}
	@PostMapping("/register")
	public ResponseEntity<userDto> createuser(@RequestBody userDto userdto){
		return new ResponseEntity<>(userservice.createUser(userdto),HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> loginuser(@RequestBody loginDto logindto){
		try {
		System.out.println(logindto.getEmail());
		System.out.println(logindto.getPassword());
			Authentication authentication=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(logindto.getEmail(),logindto.getPassword())
					);
			System.out.println(authentication);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token=jwtT.generateToken(authentication);
			JwtAuthResponse response = new JwtAuthResponse(token);
			System.out.println(response);
			return ResponseEntity.ok(response);
			// return new ResponseEntity<>(token,HttpStatus.OK);
		}
		catch(AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			//throw new invalidException("invalid Credentials");
		}
	}
	@GetMapping("/profile")
	public ResponseEntity<userDto> getProfile(Authentication authentication){
		String username = authentication.getName();
        userDto userProfile = userservice.getUserByUsername(username);
        if (userProfile != null) {
            return ResponseEntity.ok(userProfile);
        } else {
            return ResponseEntity.notFound().build();
        }
}}
