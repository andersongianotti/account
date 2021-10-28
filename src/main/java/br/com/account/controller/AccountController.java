package br.com.account.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.account.dto.AccountDto;
import br.com.account.form.AccountForm;
import br.com.account.model.Account;
import br.com.account.repository.AccountRepository;


@RestController
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping
	@RequestMapping("/reset")
	@ResponseBody
	public ResponseEntity<?> reset() {
		return ResponseEntity.ok(new String("ok"));
	}
	
	@RequestMapping("/balance")
	@ResponseBody
 	public ResponseEntity<?> lista(Long account_id) {
			Optional<Account> account = accountRepository.findById(account_id);
				
			if (account.isPresent()) {
				Account acc = new Account(account.get());
				return ResponseEntity.ok(new String(acc.getBalance().toString()));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String("0"));
	}
	@RequestMapping("/event")
	@PostMapping
	@Transactional
	public ResponseEntity<?> balance(@RequestBody @Valid AccountForm form, UriComponentsBuilder uriBuilder){
		Account account = form.converter();
		System.out.println(account.getId());
		System.out.println(account.getBalance());
		accountRepository.save(account);
		
	//	URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(account.getId()).toUri();
		//return ResponseEntity.created(uri).body(new AccountDto(account));
	return ResponseEntity.ok(new AccountDto(account));
	}


}
