package br.com.account.service;

import java.math.BigDecimal;
import java.util.Optional;

import br.com.account.form.AccountForm;
import br.com.account.model.Account;
import br.com.account.repository.AccountRepository;

public class AccountService {

	public Account tratarAccount(AccountForm form, AccountRepository accountRepository) {
		Account account = null;
		BigDecimal novoValorSaldo = null;
		if (form.getType().equals("deposit")) {
			Optional<Account> optional = accountRepository.findById(form.getDestination());
			if (optional.isPresent()) {
				account = optional.get();
				novoValorSaldo = account.getBalance().add(form.getAmount());
				account.setBalance(novoValorSaldo);
			} else {
				account = form.converter();
				accountRepository.save(account);
			}

		} else if (form.getType().equals("withdraw")) {
			Optional<Account> optional = accountRepository.findById(form.getOrigin());
			if (optional.isPresent()) {
				account = optional.get();
				novoValorSaldo = account.getBalance().subtract(form.getAmount());
				account.setBalance(novoValorSaldo);
			}
		} else if (form.getType().equals("transfer")) {
			Optional<Account> optionalDestination = accountRepository.findById(form.getDestination());
			Optional<Account> optionalOrigin = accountRepository.findById(form.getOrigin());
			if (optionalOrigin.isPresent()) {
				if (optionalDestination.isPresent()) {
					account = optionalOrigin.get();
					Account account2 = optionalDestination.get();
					novoValorSaldo = account.getBalance().subtract(form.getAmount());
					account.setBalance(novoValorSaldo);
					novoValorSaldo = account2.getBalance().add(form.getAmount());
					account2.setBalance(novoValorSaldo);
				} else {
					account = form.converter();
					accountRepository.save(account);
					Account account2 = optionalOrigin.get();
					novoValorSaldo = account2.getBalance().subtract(form.getAmount());
					account2.setBalance(novoValorSaldo);
				}

			}

		}
		return account;
	}

	public boolean transfAccount(AccountForm form, AccountRepository accountRepository) {
		Account account = null;
		BigDecimal novoValorSaldo = null;
		Optional<Account> optionalDestination = accountRepository.findById(form.getDestination());
		Optional<Account> optionalOrigin = accountRepository.findById(form.getOrigin());
		if (optionalOrigin.isPresent()) {
			if (optionalDestination.isPresent()) {
				account = optionalOrigin.get();
				Account accountDestination = optionalDestination.get();
				novoValorSaldo = account.getBalance().subtract(form.getAmount());
				account.setBalance(novoValorSaldo);
				novoValorSaldo = accountDestination.getBalance().add(form.getAmount());
				accountDestination.setBalance(novoValorSaldo);
			} else {
				account = form.converter();
				accountRepository.save(account);
				Account accountOrigin = optionalOrigin.get();
				novoValorSaldo = accountOrigin.getBalance().subtract(form.getAmount());
				accountOrigin.setBalance(novoValorSaldo);
			}
			
			return true;

		}
		return false;
	}
	
	public boolean deleteAccount(AccountRepository accountRepository) {
		
		accountRepository.deleteAll();
		return true;
		
		
	}

}