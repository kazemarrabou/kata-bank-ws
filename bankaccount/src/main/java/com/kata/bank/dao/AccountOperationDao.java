package com.kata.bank.dao;

import java.util.List;

import com.kata.bank.model.AccountOperation;

public interface AccountOperationDao {

	AccountOperation findById(int id);

	void saveUserAccount(AccountOperation accountOperation);

	List<AccountOperation> findAccountOperationByIdUser(int idUser);
}
