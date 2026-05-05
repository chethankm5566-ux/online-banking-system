package com.jsp.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.bank.dto.LoginRequest;
import com.jsp.bank.dto.RegisterRequest;
import com.jsp.bank.entity.User;
import com.jsp.bank.service.AuthService;
import com.jsp.bank.util.ApiResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("auth/api")
@AllArgsConstructor
public class AuthController {
	private final AuthService authservice;
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<User>> registerUser(@Valid @RequestBody RegisterRequest registerRequest)
	{
		return authservice.registerUser(registerRequest);
	}
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<User>> loginUser(@Valid @RequestBody LoginRequest loginrequest)
	{
		return authservice.loginUser(loginrequest);
	}

}
