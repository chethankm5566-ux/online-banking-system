package com.jsp.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.bank.entity.Account;

public interface AccountRepository  extends JpaRepository<Account, Integer>{
	Optional<Account> findByAccountNumber(long accountNumber);

}
