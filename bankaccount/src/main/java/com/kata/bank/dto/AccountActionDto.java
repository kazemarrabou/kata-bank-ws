package com.kata.bank.dto;

import com.kata.bank.enums.TypeAction;

public class AccountActionDto {

	private double amount;
	private String libelle;
	private TypeAction typeAction;

	public TypeAction getTypeAction() {
		return typeAction;
	}

	public void setTypeAction(final TypeAction typeAction) {
		this.typeAction = typeAction;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(final Double amount) {
		this.amount = amount;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(final String libelle) {
		this.libelle = libelle;
	}

}
