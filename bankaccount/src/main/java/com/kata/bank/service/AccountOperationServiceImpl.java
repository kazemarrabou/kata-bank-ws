package com.kata.bank.service;

import java.util.List;

import org.joda.time.LocalDate;

import com.kata.bank.dto.AccountActionDto;
import com.kata.bank.dto.AccountOperationDto;
import com.kata.bank.dto.UserAccountDto;

public class AccountOperationServiceImpl implements AccountOperationService {

	// TO DO faire la persistance de donnée si nous avons encore du temps.
	private final UserAccountDto compteClient = new UserAccountDto("001", 0.0, "Luc Marcheciel");

	@Override
	public UserAccountDto deposit(final AccountActionDto action) {
		compteClient.addBalance(action.getAmount());
		compteClient.getOperation().add(traceOperation(action));
		return compteClient;
	}

	@Override
	public UserAccountDto withdraw(final AccountActionDto action) throws Exception {
		if (checkIfEnough(action.getAmount(), compteClient)) {
			compteClient.delBalance(action.getAmount());
			compteClient.getOperation().add(traceOperation(action));
		} else {
			throw new Exception("impossible de faire le retrait");
		}
		return compteClient;
	}

	@Override
	public List<AccountOperationDto> findAllOperationIdUser() {
		return compteClient.getOperation();
	}

	private AccountOperationDto traceOperation(final AccountActionDto action) {
		final AccountOperationDto accountOperation = new AccountOperationDto();
		accountOperation.setDateTransaction(LocalDate.now().toString());
		accountOperation.setAmount(action.getAmount().toString());
		accountOperation.setLibelle(action.getLibelle());

		return accountOperation;
	}

	@Override
	public UserAccountDto getUserAccount() {
		return new UserAccountDto("001", 0.0, "Luc Marcheciel");
	}

	private boolean checkIfEnough(final double amount, final UserAccountDto compte) {
		if (compte.getBalance() >= amount) {
			return true;
		}
		return false;
	}

}
