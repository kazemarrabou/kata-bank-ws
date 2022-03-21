package com.kata.bank.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.kata.bank.dto.AccountActionDto;
import com.kata.bank.dto.UserAccountDto;
import com.kata.bank.enums.TypeAction;
import com.kata.bank.service.AccountOperationServiceImpl;

@RunWith(JUnit4.class)
public class AccountOperationServiceImplTest {

	@InjectMocks
	AccountOperationServiceImpl accountOperationService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void depositOK() {
		when(accountOperationService.getUserAccount()).thenReturn(getUserAccount01());
		final UserAccountDto userAccount = accountOperationService.deposit(getAccountAction());
		assertEquals("le nom du client ne correspond pas :", userAccount.getName(), "Luc Marcheciel");
		assertEquals(Double.valueOf(100.0), userAccount.getBalance(), Double.valueOf(100.0));
		assertEquals("le nom du client ne correspond pas", userAccount.getReference(), "001");
		assertEquals("un operation en plus ", userAccount.getOperation().size(), 1);
		assertEquals("un operation en plus ", userAccount.getOperation().get(0).getLibelle(), "Depot du salaire");

	}

	@Test(expected = Exception.class)
	public void withdrawKo() throws Exception {
		when(accountOperationService.getUserAccount()).thenReturn(getUserAccount01());
		final UserAccountDto userAccount = accountOperationService.withdraw(getAccountAction1());

	}

	@Test
	public void withdrawOK() throws Exception {
		when(accountOperationService.getUserAccount()).thenReturn(getUserAccount02());
		final UserAccountDto userAccount = accountOperationService.withdraw(getAccountAction2());
		assertEquals("le nom du client ne correspond pas :", userAccount.getName(), "Luc Marcheciel");
		assertEquals(Double.valueOf(100.0), userAccount.getBalance(), Double.valueOf(50.0));
		assertEquals("le nom du client ne correspond pas", userAccount.getReference(), "001");
		assertEquals("un operation en plus ", userAccount.getOperation().size(), 1);
		assertEquals("un operation en plus ", userAccount.getOperation().get(0).getLibelle(), "Achat sabre lazer");

	}

	private AccountActionDto getAccountAction() {

		final AccountActionDto accountActionDto = new AccountActionDto();
		accountActionDto.setAmount(100.0);
		accountActionDto.setLibelle("Depot du salaire");
		accountActionDto.setTypeAction(TypeAction.DEPOSIT);

		return accountActionDto;
	}

	private AccountActionDto getAccountAction1() {

		final AccountActionDto accountActionDto = new AccountActionDto();
		accountActionDto.setAmount(100.0);
		accountActionDto.setLibelle("Achat vaisseux");
		accountActionDto.setTypeAction(TypeAction.RETRIEVE);

		return accountActionDto;
	}

	private AccountActionDto getAccountAction2() {

		final AccountActionDto accountActionDto = new AccountActionDto();
		accountActionDto.setAmount(50.0);
		accountActionDto.setLibelle("Achat sabre lazer");
		accountActionDto.setTypeAction(TypeAction.RETRIEVE);

		return accountActionDto;
	}

	private UserAccountDto getUserAccount01() {
		return new UserAccountDto("001", 0.0, "Luc Marcheciel");
	}

	private UserAccountDto getUserAccount02() {
		return new UserAccountDto("001", 100.0, "Luc Marcheciel");
	}

}
