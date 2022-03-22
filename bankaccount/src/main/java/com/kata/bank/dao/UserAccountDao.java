package com.kata.bank.dao;

import com.kata.bank.model.UserAccount;

public interface UserAccountDao {

	UserAccount findById(int id);

	void saveUserAccount(UserAccount userAccount);

}
