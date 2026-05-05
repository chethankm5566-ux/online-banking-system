package com.jsp.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.bank.dto.TransctionRequest;
import com.jsp.bank.entity.Transaction;
import com.jsp.bank.service.TransctionService;
import com.jsp.bank.util.ApiResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/transaction/api")
@AllArgsConstructor
public class TransctionController {

	private final TransctionService transactionservice;
	@PostMapping("/deposit")
	public ResponseEntity<ApiResponse<Double>> deposit(@RequestBody TransctionRequest transctionRequest)
	{
		return transactionservice.deposit(transctionRequest);
	}
	@PostMapping("/withdraw")
	public ResponseEntity<ApiResponse<Double>> withdraw(@Valid @RequestBody TransctionRequest transctionRequest)
	{
		return transactionservice.withdraw(transctionRequest);
	}
	@PostMapping("/transfer")
	public ResponseEntity<ApiResponse<Double>> transfer(@RequestBody TransctionRequest transctionRequest)
	{
		return transactionservice.transfer(transctionRequest);
	}
	@GetMapping("/history/{accountNumber}")
	public ResponseEntity<ApiResponse<List<Transaction>>> fetchTransactionHistory(@PathVariable long accountNumber)
	{
		return transactionservice.fetchTransactionHistory(accountNumber);
	}
}
