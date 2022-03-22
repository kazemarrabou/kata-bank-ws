package com.kata.bank.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kata.bank.controller.AccountActionController;
import com.kata.bank.dto.UserAccountDto;
import com.kata.bank.service.AccountOperationService;

public class AccountActionControllerTest {
	@Mock
	AccountOperationService accountOperationService;

	@InjectMocks
	AccountActionController accountActionController;

	MockMvc mockMvc;

	@BeforeClass
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(accountActionController).build();
	}

	@Test
	void getUserAccount() throws Exception {
		when(accountOperationService.getUserAccount(Mockito.any())).thenReturn(getUserAccount01());
		mockMvc.perform(get("/user/{id}", 1).contentType("application/json").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.reference").value("1")).andExpect(jsonPath("$.balance").value("0.0"))
				.andExpect(jsonPath("$.name").value("Luc Marcheciel"));
	}

	private UserAccountDto getUserAccount01() {
		final UserAccountDto user = new UserAccountDto();
		user.setReference("1");
		user.setBalance(0.0);
		user.setName("Luc Marcheciel");
		return user;
	}

}
