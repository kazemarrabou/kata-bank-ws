package com.kata.bank.dao;

import com.kata.bank.model.UserAccount;

public class UserAccountDaoImpl extends AbstractDao<Integer, UserAccount> implements UserAccountDao {

	@Override
	public UserAccount findById(final int id) {
		return getByKey(id);
	}

	@Override
	public void saveUserAccount(final UserAccount userAccount) {
		persist(userAccount);
	}

}
