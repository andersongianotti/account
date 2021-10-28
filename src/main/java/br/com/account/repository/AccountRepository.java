package br.com.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.account.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
