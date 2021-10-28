package br.com.account.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.account.model.Account;
import br.com.account.repository.AccountRepository;


@RestController
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@RequestMapping("/reset")
	@ResponseBody
	public String reset() {
		return "Hello World!";
	}
	
	@RequestMapping("/balance")
 	public ResponseEntity<?> lista(Long account_id) {
			Optional<Account> account = accountRepository.findById(account_id);
				
			if (account.isPresent()) {
				Account acc = account.get();
				return ResponseEntity.ok(new String(acc.getBalance().toString()));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String("0"));
	}

}
