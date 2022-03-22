package com.kata.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import com.kata.bank.dao.AccountOperationDao;
import com.kata.bank.dao.UserAccountDao;
import com.kata.bank.dto.AccountActionDto;
import com.kata.bank.dto.AccountOperationDto;
import com.kata.bank.dto.UserAccountDto;
import com.kata.bank.enums.TypeAction;
import com.kata.bank.model.AccountOperation;
import com.kata.bank.model.UserAccount;

public class AccountOperationServiceImpl implements AccountOperationService {

	// TO DO faire la persistance de donnée si nous avons encore du temps.
	@Autowired
	private UserAccountDao userAccountDao;

	@Autowired
	private AccountOperationDao accountOperationDao;

	public UserAccountDto getUserAccount(final int idUser) {
		final UserAccount user = userAccountDao.findById(idUser);
		return translateToUserAccountDto(user);
	}

	@Override
	public UserAccountDto deposit(final AccountActionDto action) {
		final UserAccount user = userAccountDao.findById(action.getIdUser());
		final double balanceWith = user.getBalance() + action.getAmount();
		user.setBalance(balanceWith);
		userAccountDao.saveUserAccount(user);

		return translateToUserAccountDto(user);
	}

	@Override
	public UserAccountDto withdraw(final AccountActionDto action) throws Exception {
		final UserAccount user = userAccountDao.findById(action.getIdUser());

		if (checkIfEnough(action.getAmount(), user)) {
			final double balanceWithOut = user.getBalance() - action.getAmount();
			user.setBalance(balanceWithOut);
			userAccountDao.saveUserAccount(user);
			traceOperation(action);
		} else {
			throw new Exception("impossible de faire le retrait");
		}
		return translateToUserAccountDto(user);
	}

	@Override
	public List<AccountOperationDto> findAllOperationByIdUser(final int idUser) {
		return translateToListAccountOperationDto(accountOperationDao.findAccountOperationByIdUser(idUser));
	}

	private void traceOperation(final AccountActionDto action) {
		final AccountOperation accountOperation = new AccountOperation();
		accountOperation.setIdUser(action.getIdUser());
		accountOperation.setDateTransaction(LocalDate.now().toString());
		if (action.getTypeAction().equals(TypeAction.RETRIEVE)) {
			accountOperation.setAmount("-" + action.getAmount().toString());
		}
		accountOperation.setAmount(action.getAmount().toString());
		accountOperation.setLibelle(action.getLibelle());

		accountOperationDao.saveUserAccount(accountOperation);

	}

	private boolean checkIfEnough(final double amount, final UserAccount compte) {
		if (compte.getBalance() >= amount) {
			return true;
		}
		return false;
	}

	private UserAccountDto translateToUserAccountDto(final UserAccount user) {
		final UserAccountDto userdto = new UserAccountDto();
		userdto.setBalance(user.getBalance());
		userdto.setName(user.getName());
		userdto.setReference(String.valueOf(user.getId()));
		return userdto;
	}

	private List<AccountOperationDto> translateToListAccountOperationDto(final List<AccountOperation> operationsUser) {
		final List<AccountOperationDto> operationdto = new ArrayList<>();
		operationsUser.forEach(op -> {
			operationdto.add(new AccountOperationDto(op.getDateTransaction(), op.getLibelle(), op.getAmount()));
		});
		final List<AccountOperationDto> operations = new ArrayList<>();
		return operationdto;
	}

}
