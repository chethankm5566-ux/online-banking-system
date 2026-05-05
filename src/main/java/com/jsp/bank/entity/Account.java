package com.jsp.bank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long accountNumber;
	private String type;
	private double balance;
	@OneToOne
	@JoinColumn
	@JsonBackReference
	private User user;
	public Account(long accountNumber, String type, double balance, User user) {
		super();
		this.accountNumber = accountNumber;
		this.type = type;
		this.balance = balance;
		this.user = user;
	}
	

}
