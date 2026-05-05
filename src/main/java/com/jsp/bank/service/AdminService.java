package com.jsp.bank.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.bank.dao.Accountdao;
import com.jsp.bank.dao.Transctiondao;
import com.jsp.bank.dao.Userdao;
import com.jsp.bank.entity.Account;
import com.jsp.bank.entity.Transaction;
import com.jsp.bank.entity.User;
import com.jsp.bank.exception.ResourceNotFoundException;
import com.jsp.bank.util.ApiResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminService {
private final Userdao userdao;
private final Accountdao accountdao;
private final Transctiondao transctiondao;
public ResponseEntity<ApiResponse<List<User>>> fetchAllUsers()
{
	List<User> list=userdao.findAll();
	if(!list.isEmpty())
	{
	ApiResponse<List<User>> apiResponse = new ApiResponse<List<User>>(HttpStatus.OK.value(),"user found",list);
	return new ResponseEntity<ApiResponse<List<User>>>(apiResponse,HttpStatus.OK);
	}
	else
	{
		throw new ResourceNotFoundException("no users exists");
	}
}


public ResponseEntity<ApiResponse<List<Account>>> fetchAllAccounts()
{
	List<Account> list = accountdao.findAll();
	if(!list.isEmpty())
	{
	ApiResponse<List<Account>> apiResponse = new ApiResponse<List<Account>>(HttpStatus.OK.value(),"account fetched sucessfully",list);
	return new ResponseEntity<ApiResponse<List<Account>>>(apiResponse,HttpStatus.OK);
	}
	else
	{
		throw new ResourceNotFoundException("no account exists");
	}
}

public ResponseEntity<ApiResponse<List<Transaction>>> fetchAllTransctions()
{
	List<Transaction> list = transctiondao.findAll();
	if(!list.isEmpty())
	{
	ApiResponse<List<Transaction>> apiResponse = new ApiResponse<List<Transaction>>(HttpStatus.OK.value(),"transction fetched sucessfully",list);
	return new ResponseEntity<ApiResponse<List<Transaction>>>(apiResponse,HttpStatus.OK);
	}
	else
	{
		throw new ResourceNotFoundException("no transction exists");
	}
}



}
