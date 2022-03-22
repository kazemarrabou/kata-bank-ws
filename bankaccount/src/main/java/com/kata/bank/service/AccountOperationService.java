package com.kata.bank.service;

import java.util.List;

import com.kata.bank.dto.AccountActionDto;
import com.kata.bank.dto.AccountOperationDto;
import com.kata.bank.dto.UserAccountDto;

public interface AccountOperationService {

	UserAccountDto deposit(AccountActionDto action);

	UserAccountDto withdraw(AccountActionDto action) throws Exception;

	UserAccountDto getUserAccount(int id);

	List<AccountOperationDto> findAllOperationByIdUser(int idUser);

}
