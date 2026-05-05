package com.jsp.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.bank.entity.Account;
import com.jsp.bank.entity.Transaction;

public interface TransctionRepository  extends JpaRepository<Transaction, Integer>{
	List<Transaction> findBySourceaccount_AccountNumberOrDestinationAccount_AccountNumber(long SourceAccount,long destinationAccount);

}
