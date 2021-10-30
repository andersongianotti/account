package br.com.account.dto;

public class TypeDto {
	
	public TypeDto(AccountDto destination) {
		this.destination = destination;
	}
	
	private AccountDto destination ;

	public AccountDto getDestination() {
		return destination;
	}

	public void setDestination(AccountDto destination) {
		this.destination = destination;
	}
	
	

}
