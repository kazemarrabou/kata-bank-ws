package com.kata.bank.dto;

public class AccountOperationDto {

	private String dateTransaction;
	private String amount;
	private String libelle;

	public AccountOperationDto() {
	}

	public AccountOperationDto(final String dateTransaction, final String libelle, final String amount) {
		this.dateTransaction = dateTransaction;
		this.amount = amount;
		this.libelle = libelle;
	}

	public String getDateTransaction() {
		return dateTransaction;
	}

	public void setDateTransaction(final String localDate) {
		this.dateTransaction = localDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(final String amount) {
		this.amount = amount;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}

}
