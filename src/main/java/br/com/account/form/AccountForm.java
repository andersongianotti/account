package br.com.account.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.account.model.Account;
import br.com.account.repository.AccountRepository;

public class AccountForm {

	@NotNull @NotEmpty
	private String type ;
	@NotNull 
	private Long destination;
	@NotNull 
	private BigDecimal amount ;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getDestination() {
		return destination;
	}
	public void setDestination(Long destination) {
		this.destination = destination;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Account converter(AccountRepository accountRepository) {
		
		Account account = new Account(accountRepository.findById(this.destination).get());
		return account;
	}
	public Account converter() {
		
		Account account = new Account(this.getDestination(),this.amount);
		return account;
	}


}
