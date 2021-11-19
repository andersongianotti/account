package br.com.account.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.account.form.AccountForm;
import br.com.account.model.Account;
import br.com.account.repository.AccountRepository;

public class AccountServiceTest {
	
	@InjectMocks
	AccountService accountService ;
	
	@Mock
	AccountRepository accountRepository;
	
	@Before
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void testTratarAccountComIdExistente() {

		Optional<Account> optAcc = Optional.of(new Account("100", new BigDecimal(10)));
		AccountForm accountForm = new AccountForm();
		accountForm.setType("deposit");
		accountForm.setDestination("100");
		accountForm.setAmount(new BigDecimal(10));
		
		Mockito.when(accountRepository.findById("100")).thenReturn(optAcc);
		Account account = accountService.tratarAccount(accountForm, accountRepository);
		Assert.assertNotNull(account);
		//Mockito.when(leilaoDao.buscarLeiloesExpirados())
		//.thenReturn(leiloes);

	}

	@Test
	public void testTratarAccountComIdNaoExistente() {
		Account account = null;
		//Optional<Account> optAcc = Optional.of(new Account("200", new BigDecimal(10)));
		Optional<Account> optAcc = Optional.ofNullable(account);
		AccountForm accountForm = new AccountForm();
		accountForm.setType("deposit");
		accountForm.setDestination("200");
		accountForm.setAmount(new BigDecimal(10));
		
		Mockito.when(accountRepository.findById("200")).thenReturn(optAcc);
		account = accountService.tratarAccount(accountForm, accountRepository);
		Assert.assertNotNull(account);
	}


	@Test
	public void testTratarAccountComWithDraw() {
		Optional<Account> optAcc = Optional.of(new Account("100", new BigDecimal(10)));
		AccountForm accountForm = new AccountForm();
		accountForm.setType("withdraw");
		accountForm.setOrigin("100");
		accountForm.setAmount(new BigDecimal(10));
		
		Mockito.when(accountRepository.findById("100")).thenReturn(optAcc);
		Account account = accountService.tratarAccount(accountForm, accountRepository);
		Assert.assertNotNull(account);
		//Mockito.when(leilaoDao.buscarLeiloesExpirados())
		//.thenReturn(leiloes);
	}

	
	@Test
	public void testTransfAccountDestinationPresent() {
		Optional<Account> optionalDestination = Optional.of(new Account("100", new BigDecimal(10)));
		Optional<Account> optionalOrigin = Optional.of(new Account("300", new BigDecimal(10)));
		AccountForm accountForm = new AccountForm();
		accountForm.setType("transfer");
		accountForm.setDestination("100");
		accountForm.setOrigin("300");
		accountForm.setAmount(new BigDecimal(10));
		
		Mockito.when(accountRepository.findById("100")).thenReturn(optionalDestination);
		Mockito.when(accountRepository.findById("300")).thenReturn(optionalOrigin);
		Assert.assertTrue(accountService.transfAccount(accountForm, accountRepository));
		
	}

	@Test
	public void testTransfAccountDestinationNaoPresente() {
		Account account = null;
		Optional<Account> optionalDestination = Optional.ofNullable(account);
		Optional<Account> optionalOrigin = Optional.of(new Account("300", new BigDecimal(10)));
		AccountForm accountForm = new AccountForm();
		accountForm.setType("transfer");
		accountForm.setDestination("100");
		accountForm.setOrigin("300");
		accountForm.setAmount(new BigDecimal(10));
		
		Mockito.when(accountRepository.findById("100")).thenReturn(optionalDestination);
		Mockito.when(accountRepository.findById("300")).thenReturn(optionalOrigin);
		Assert.assertTrue(accountService.transfAccount(accountForm, accountRepository));
		
	}

	@Test
	public void testTransfAccountOriginNaoPresente() {
		Account account = null;
		Optional<Account> optionalDestination = Optional.ofNullable(account);
		Optional<Account> optionalOrigin = Optional.ofNullable(account);
		AccountForm accountForm = new AccountForm();
		accountForm.setType("transfer");
		accountForm.setDestination("100");
		accountForm.setOrigin("300");
		accountForm.setAmount(new BigDecimal(10));
		
		Mockito.when(accountRepository.findById("100")).thenReturn(optionalDestination);
		Mockito.when(accountRepository.findById("300")).thenReturn(optionalOrigin);
		Assert.assertFalse(accountService.transfAccount(accountForm, accountRepository));
		
	}

	
}
