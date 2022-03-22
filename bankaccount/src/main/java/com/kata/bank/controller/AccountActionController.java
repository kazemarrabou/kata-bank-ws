package com.kata.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kata.bank.dto.AccountActionDto;
import com.kata.bank.dto.AccountOperationDto;
import com.kata.bank.dto.UserAccountDto;
import com.kata.bank.enums.TypeAction;
import com.kata.bank.service.AccountOperationService;

public class AccountActionController {

	@Autowired
	AccountOperationService operationService;

	// ------------------- Deposit/retrieve money
	// --------------------------------------------------------

	@PostMapping(value = "/action")
	@ResponseBody
	public ResponseEntity<?> accountActionTransaction(@RequestBody final AccountActionDto accountAction) {
		System.out.println("AccountActionController.depositMoney - entree dans la methode avec les parametres :  "
				+ accountAction.toString());
		UserAccountDto compteClient;

		if (accountAction.getAmount().doubleValue() == 0) {
			return ResponseEntity.badRequest().body(" impossible de faire la transaction aucun montant indiqué");
		}

		if (accountAction.getTypeAction().equals(TypeAction.DEPOSIT)) {
			compteClient = operationService.deposit(accountAction);
		} else {
			try {
				compteClient = operationService.withdraw(accountAction);
			} catch (final Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(compteClient);
	}

	// ------------------- operation list
	// --------------------------------------------------------

	@GetMapping(value = "/operation")
	public ResponseEntity<List<AccountOperationDto>> listOperation(final int idUser) {
		System.out.println("AccountActionController.listOperation - entree dans la methode avec les parametres : ");

		final List<AccountOperationDto> listOperation = operationService.findAllOperationByIdUser(idUser);
		return ResponseEntity.status(HttpStatus.OK).body(listOperation);
	}

	// ------------------- Get User Account
	// --------------------------------------------------------

	@GetMapping(value = "/user/{id}")
	@ResponseBody
	public ResponseEntity<UserAccountDto> getUserAccount(@PathVariable final int idUser) {
		System.out.println("entree dans la methode -  AccountActionController.getUserAccount ");

		final UserAccountDto account = operationService.getUserAccount(idUser);
		return ResponseEntity.status(HttpStatus.OK).body(account);
	}
}
