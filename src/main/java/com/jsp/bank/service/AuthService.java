package com.jsp.bank.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.bank.dao.Userdao;
import com.jsp.bank.dto.LoginRequest;
import com.jsp.bank.dto.RegisterRequest;
import com.jsp.bank.entity.Account;
import com.jsp.bank.entity.User;
import com.jsp.bank.exception.InvalidCredentialsException;
import com.jsp.bank.exception.ResourceNotFoundException;
import com.jsp.bank.util.AccountUtil;
import com.jsp.bank.util.ApiResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {
private final Userdao userdao;
public ResponseEntity<ApiResponse<User>> registerUser(RegisterRequest registerRequest)
{
	User user = new User(registerRequest.getName(), registerRequest.getPhone(), registerRequest.getEmail(), registerRequest.getPassword(), registerRequest.getRole());
	Account account = new Account(AccountUtil.generateAccountNumber(), "savings", 0, user);
	user.setAccount(account);
	User user2 = userdao.save(user);
	ApiResponse<User> apiResponse = new ApiResponse<User>(HttpStatus.CREATED.value(),"user and account created sucesfuuly",user2);
	return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
}
public ResponseEntity<ApiResponse<User>> loginUser(LoginRequest loginrequest)
{
	Optional<User> optional = userdao.FindByEmail(loginrequest.getEmail());
	if(optional.isPresent())
	{
		User user = optional.get();
		if(!user.getPassword().equals(loginrequest.getPassword()))
         {
	       throw new InvalidCredentialsException("inalid passwrod");
           }
         ApiResponse<User> apiResponse = new ApiResponse<User>(HttpStatus.OK.value(),"user sucessfully logged in",user);
         return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	

	}
	else
	{
		throw new ResourceNotFoundException("user with the email not found");
	}
}
	
	
}

