package com.jsp.bank.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsp.bank.entity.Account;
import com.jsp.bank.entity.Transaction;
import com.jsp.bank.repository.TransctionRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class Transctiondao {
private final TransctionRepository transctionRepository;
public Transaction save(Transaction transaction)
{
	return transctionRepository.save(transaction);
}
public List<Transaction> findAll()
{
	return transctionRepository.findAll();
}
public List<Transaction> findBySourceAccountOrDestinationAccount(long sourceAccount,long destinationAccount)
{
	return transctionRepository.findBySourceaccount_AccountNumberOrDestinationAccount_AccountNumber(sourceAccount, destinationAccount);
}
}
