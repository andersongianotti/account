package br.com.account.dto;

public class TransfDto {

	public TransfDto(AccountDto origin, AccountDto destination) {
		this.origin = origin ;
		this.destination = destination;
	}
	private AccountDto origin ;
	private AccountDto destination ;
	public AccountDto getOrigin() {
		return origin;
	}
	public void setOrigin(AccountDto origin) {
		this.origin = origin;
	}
	public AccountDto getDestination() {
		return destination;
	}
	public void setDestination(AccountDto destination) {
		this.destination = destination;
	}
	
	
}
