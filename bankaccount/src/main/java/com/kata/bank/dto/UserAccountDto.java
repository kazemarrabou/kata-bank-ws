package com.kata.bank.dto;

import java.util.ArrayList;
import java.util.List;

public class UserAccountDto {

	private String reference;
	private double balance;
	private String name;
	private List<AccountOperationDto> operations;

	public UserAccountDto(final String reference, final double balance, final String name) {
		this.reference = reference;
		this.balance = balance;
		this.name = name;
		this.operations = new ArrayList<>();
	}

	public String getReference() {
		return reference;
	}

	public void setReference(final String reference) {
		this.reference = reference;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void addBalance(final double balance) {
		this.balance += balance;
	}

	public void delBalance(final double balance) {
		this.balance -= balance;
	}

	public List<AccountOperationDto> getOperation() {
		return operations;
	}

	public void setOperation(final List<AccountOperationDto> operation) {
		this.operations = operation;
	}
}
