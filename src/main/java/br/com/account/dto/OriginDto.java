package br.com.account.dto;

public class OriginDto {
	
	public OriginDto(AccountDto origin) {
		this.origin = origin;
	}
	
	private AccountDto origin;

	public AccountDto getOrigin() {
		return origin;
	}

	public void setOrigin(AccountDto origin) {
		this.origin = origin;
	}
	

}
