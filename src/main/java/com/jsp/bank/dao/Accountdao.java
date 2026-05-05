package com.jsp.bank.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jsp.bank.entity.Account;
import com.jsp.bank.repository.AccountRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class Accountdao {
	private final AccountRepository accountRepository;

	public Account save(Account account)
	{
		return accountRepository.save(account);
	}
	public List<Account> findAll()
	{
		return accountRepository.findAll();
	}
	public Optional<Account> findByAccountNumber(long accountNumber)
	{
		return accountRepository.findByAccountNumber(accountNumber);
	}
}
