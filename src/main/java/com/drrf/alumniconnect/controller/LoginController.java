package com.drrf.alumniconnect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drrf.alumniconnect.model.UserDetails;
import com.drrf.alumniconnect.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;

	
	@GetMapping("/login/{user}") 
	public UserDetails retrieveStudent(@RequestBody UserDetails user) { 
		UserDetails userDetails = loginService.getUserDetails(user);
		return userDetails;
	}

	
	@GetMapping("/login/{id}")
	public UserDetails retrieveStudent(@PathVariable long id) {
		UserDetails user = new UserDetails("phani@gmail.com","test");
		UserDetails userDetails = loginService.getUserDetails(user);
		System.out.println(userDetails.toString());
		return userDetails;
	}
	

}
