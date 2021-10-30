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
import br.com.account.dto.OriginDto;
import br.com.account.dto.TransfDto;
import br.com.account.dto.TypeDto;
import br.com.account.form.AccountForm;
import br.com.account.model.Account;
import br.com.account.repository.AccountRepository;
import br.com.account.service.AccountService;


@RestController
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping
	@RequestMapping("/reset")
	@ResponseBody
	public ResponseEntity<?> reset() {
		AccountService accountService = new AccountService();
		accountService.deleteAccount(accountRepository);
		return ResponseEntity.ok(new String("OK"));
	}
	
	@RequestMapping("/balance")
	@ResponseBody
 	public ResponseEntity<?> lista(String account_id) {
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
		//Account account = form.converter();
		AccountService accountService = new AccountService();
		if(form.getType().equals("transfer")) {
			if(accountService.transfAccount(form, accountRepository)) {
				Optional<Account> optionalDestination = accountRepository.findById(form.getDestination());
				Account accountDestination = optionalDestination.get();
				Optional<Account> optionalOrigin = accountRepository.findById(form.getOrigin());
				Account accountOrigin = optionalOrigin.get();
				URI uri = uriBuilder.path("/event").buildAndExpand(accountOrigin.getId()).toUri();
				return ResponseEntity.created(uri).body(new TransfDto(new AccountDto(accountOrigin),
						new AccountDto(accountDestination)));
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String("0"));
			}
		}else {
			Account account = accountService.tratarAccount(form, accountRepository);
			if(account!=null) {
				URI uri = uriBuilder.path("/event").buildAndExpand(account.getId()).toUri();
				if(form.getDestination()!=null) {
					return ResponseEntity.created(uri).body(new TypeDto(new AccountDto(account)));
				}
				else {
					return ResponseEntity.created(uri).body(new OriginDto(new AccountDto(account)));
				}
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new String("0"));
			}
			
		}
	}
}