package com.osmonaliev.accountservice.controller;

import com.osmonaliev.accountservice.dto.AccountRequestDto;
import com.osmonaliev.accountservice.dto.AccountResponseDto;
import com.osmonaliev.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
private  final AccountService accountService;
@Autowired
public AccountController(AccountService accountService) {
	this.accountService = accountService;
}

@GetMapping("/{accountId}")
public AccountResponseDto getAccount(@PathVariable Long accountId) {
return new AccountResponseDto(accountService.getAccountById(accountId));
}
@PostMapping("/")
public Long createAccount(@RequestBody AccountRequestDto accountRequestDto){
return accountService.createAccount(accountRequestDto.getName(),accountRequestDto.getEmail(),accountRequestDto.getPhone(),accountRequestDto.getBills());
}
@PutMapping("/{accountId}")
public AccountResponseDto updateAccountId(@PathVariable Long accountId,
		@RequestBody AccountRequestDto accountRequestDto){
	return new AccountResponseDto(accountService.updateAccount(accountId,accountRequestDto.getName(),accountRequestDto.getEmail(),accountRequestDto.getPhone(),accountRequestDto.getBills()));

}
@DeleteMapping("/{accountId}")
public AccountResponseDto deleteAccount(@PathVariable Long accountId){
	return new AccountResponseDto(accountService.deleteAccount(accountId));
}
}
