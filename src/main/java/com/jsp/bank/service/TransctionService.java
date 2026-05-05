package com.jsp.bank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.jsp.bank.dao.Accountdao;
import com.jsp.bank.dao.Transctiondao;
import com.jsp.bank.dto.TransctionRequest;
import com.jsp.bank.entity.Account;
import com.jsp.bank.entity.Transaction;
import com.jsp.bank.exception.InsufficeientBalanceException;
import com.jsp.bank.exception.ResourceNotFoundException;
import com.jsp.bank.util.ApiResponse;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class TransctionService {
	private final Accountdao accountdao;
	private final Transctiondao transctiondao;
	public ResponseEntity<ApiResponse<Double>> deposit(TransctionRequest transctionRequest)
	{
		Optional<Account> optional = accountdao.findByAccountNumber(transctionRequest.getSourceAccountNumber());
		if(optional.isPresent())
		{
			Account account = optional.get();
			account.setBalance(account.getBalance()+transctionRequest.getAmount());
			Transaction transaction = new Transaction("DEPOSIT", transctionRequest.getAmount(), LocalDate.now(), account, null, "SUCCESS");
			Account acc = accountdao.save(account);
			transctiondao.save(transaction);
			ApiResponse<Double> apiResponse = new ApiResponse<Double>(HttpStatus.CREATED.value(),"Amont  sucesfully deposited",acc.getBalance());
		   return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);	
		}
		else
		{
			throw new ResourceNotFoundException("account not found");
		}
	}
	public ResponseEntity<ApiResponse<Double>> withdraw(TransctionRequest transctionRequest)
	{
		Optional<Account> optional = accountdao.findByAccountNumber(transctionRequest.getSourceAccountNumber());
		if(optional.isPresent())
		{
			Account account = optional.get();
			if(transctionRequest.getAmount()>account.getBalance())
			{
				throw new InsufficeientBalanceException ("request amount is more than the original balance");
			}
			account.setBalance(account.getBalance()-transctionRequest.getAmount());
			Transaction transaction = new Transaction("WITHDRAW", transctionRequest.getAmount(), LocalDate.now(), account, null, "SUCCES");
			Account acc = accountdao.save(account);
			transctiondao.save(transaction);
			ApiResponse<Double> apiResponse = new ApiResponse<Double>(HttpStatus.CREATED.value(),"Amont  sucesfully withdrawn",acc.getBalance());
			   return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);	
		}
		else
		{
			throw new ResourceNotFoundException("account not found");
		}
		
		
		
	}
	public ResponseEntity<ApiResponse<Double>> transfer(TransctionRequest transctionRequest)
	{
		Optional<Account> optional1 = accountdao.findByAccountNumber(transctionRequest.getSourceAccountNumber());
		Optional<Account> optional2 = accountdao.findByAccountNumber(transctionRequest.getDestinationAccountNumber());
		if(optional1.isPresent()&&optional2.isPresent())
		{
			Account sender = optional1.get();
			Account receiver = optional2.get();
			if(transctionRequest.getAmount()>sender.getBalance())
			{
				throw new InsufficeientBalanceException ("request amount is more than the original balance");
			}
			sender.setBalance(sender.getBalance()-transctionRequest.getAmount());
			receiver.setBalance(receiver.getBalance()+transctionRequest.getAmount());
			Transaction transaction = new Transaction("TRANSFER", transctionRequest.getAmount(), LocalDate.now(), sender, receiver, "SUCCESS");
			Account acc = accountdao.save(sender);
		    accountdao.save(receiver);
			transctiondao.save(transaction);
			ApiResponse<Double> apiResponse = new ApiResponse<Double>(HttpStatus.CREATED.value(),"Amont  sucesfully transfer",acc.getBalance());
			   return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);	
			
		}
		else
		{
			throw new ResourceNotFoundException("account not found");
		}
		
	}
	public ResponseEntity<ApiResponse<List<Transaction>>> fetchTransactionHistory(long accountNumber)
	{
		List<Transaction> list = transctiondao.findBySourceAccountOrDestinationAccount(accountNumber, accountNumber);
ApiResponse<List<Transaction>> apiResponse = new ApiResponse<List<Transaction>>(HttpStatus.OK.value(),"Tranasction history fetched successfull",list);
return new ResponseEntity<>(apiResponse,HttpStatus.OK);	
		
	}

}
