package com.kata.bank.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class AccountOperation {

	@Id
	@GeneratedValue
	@Column(name = "OPERATION_ID")
	private int id;

	@Column(name = "DATE_TRANSACTION")
	private String dateTransaction;

	@Column(name = "AMOUNT")
	private String amount;

	@Column(name = "LIBELLE")
	private String libelle;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private int idUser;

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

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(final int idUser) {
		this.idUser = idUser;
	}

}
