package br.com.account.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.account.model.Account;

public class AccountDto {
	
	public AccountDto(Account account) {
		this.balance = account.getBalance();
	}

	private BigDecimal balance ;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public static List<AccountDto> converter(List<Account> accounts) {
		return accounts.stream().map(AccountDto::new).collect(Collectors.toList());
	}

}
