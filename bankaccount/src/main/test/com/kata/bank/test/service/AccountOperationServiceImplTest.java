package com.kata.bank.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.kata.bank.dao.AccountOperationDao;
import com.kata.bank.dao.UserAccountDao;
import com.kata.bank.dto.AccountActionDto;
import com.kata.bank.dto.UserAccountDto;
import com.kata.bank.enums.TypeAction;
import com.kata.bank.model.UserAccount;
import com.kata.bank.service.AccountOperationServiceImpl;

@RunWith(JUnit4.class)
public class AccountOperationServiceImplTest {

	@InjectMocks
	AccountOperationServiceImpl accountOperationService;

	@Mock
	UserAccountDao userAccountDao;

	@Mock
	AccountOperationDao accountOperationDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getUserAccountOK() {
		when(userAccountDao.findById(Mockito.any())).thenReturn(getUserAccount01());
		final UserAccountDto userAccount = accountOperationService.getUserAccount(1);
		assertEquals("le nom du client ne correspond pas :", userAccount.getName(), "Luc Marcheciel");
		assertEquals(Double.valueOf(100.0), userAccount.getBalance(), Double.valueOf(100.0));
		assertEquals("le nom du client ne correspond pas", userAccount.getReference(), "001");
	}

	@Test
	public void depositOK() {
		when(userAccountDao.findById(Mockito.any())).thenReturn(getUserAccount01());
		final UserAccountDto userAccount = accountOperationService.deposit(getAccountAction());
		assertEquals("le nom du client ne correspond pas :", userAccount.getName(), "Luc Marcheciel");
		assertEquals(Double.valueOf(100.0), userAccount.getBalance(), Double.valueOf(100.0));
		assertEquals("un operation en plus ", userAccount.getOperation().size(), 1);
		assertEquals("un operation en plus ", userAccount.getOperation().get(0).getLibelle(), "Depot du salaire");

	}

	@Test(expected = Exception.class)
	public void withdrawKo() throws Exception {
		when(userAccountDao.findById(Mockito.any())).thenReturn(getUserAccount01());
		final UserAccountDto userAccount = accountOperationService.withdraw(getAccountAction1());

	}

	@Test
	public void withdrawOK() throws Exception {
		when(userAccountDao.findById(Mockito.any())).thenReturn(getUserAccount02());
		final UserAccountDto userAccount = accountOperationService.withdraw(getAccountAction2());
		assertEquals("le nom du client ne correspond pas :", userAccount.getName(), "Luc Marcheciel");
		assertEquals(Double.valueOf(100.0), userAccount.getBalance(), Double.valueOf(100.0));
		assertEquals("le nom du client ne correspond pas", userAccount.getReference(), "001");
		assertEquals("un operation en plus ", userAccount.getOperation().size(), 1);
		assertEquals("le libelle n'est pas correct ", userAccount.getOperation().get(0).getLibelle(),
				"Achat sabre lazer");
		assertEquals("la somme deduis n'est pas correct ", userAccount.getOperation().get(0).getLibelle(), "-100");

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

	private UserAccount getUserAccount01() {
		final UserAccount user = new UserAccount();
		user.setId(1);
		user.setBalance(0.0);
		user.setName("Luc Marcheciel");
		return user;
	}

	private UserAccount getUserAccount02() {
		final UserAccount user = new UserAccount();
		user.setId(1);
		user.setBalance(200.0);
		user.setName("Luc Marcheciel");
		return user;
	}

}
