package br.com.account.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.account.model.Account;

public class AccountDto {
	
	public AccountDto(Account account) {
		this.id = account.getId();
		this.balance_1 = account.getBalance();
	}

	private String id;
	private BigDecimal balance_1 ;

	public BigDecimal getBalance_1() {
		return balance_1;
	}

	public void setBalance_1(BigDecimal balance) {
		this.balance_1 = balance;
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static List<AccountDto> converter(List<Account> accounts) {
		return accounts.stream().map(AccountDto::new).collect(Collectors.toList());
	}

}
