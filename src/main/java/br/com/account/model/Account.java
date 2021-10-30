package br.com.account.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity      
public class Account {
	
	@Id 
	private String id ;
	
	private BigDecimal balance;
	
	public Account(String id, BigDecimal balance) {
		this.id = id;
		this.balance = balance;
	}
	
	public Account(Account account ) {
		this.id = account.id;
		this.balance = account.balance;		
	}
	
	public Account() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(balance, other.balance) && id == other.id;
	}
	

}
