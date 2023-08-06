package com.healthcare.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.authenticationservice.exception.UserNotValidException;
import com.healthcare.authenticationservice.model.UserDetails;
import com.healthcare.authenticationservice.utils.JwtUtil;

@RestController
public class AuthenticationController {
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody UserDetails user )
	{
		String token = null;
		//user present in patient table,generate the token
		if(user.getName() !=null) {
			token = jwtUtil.generateToken(user);
		}
		else
		{
			//user not present,throw error
			throw new UserNotValidException();
		}
		
		//verify jwt token
		jwtUtil.verify(token);
		return token;

		
	}

}
