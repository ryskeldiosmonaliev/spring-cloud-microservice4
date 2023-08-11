package com.osmonaliev.accountservice.service;

import com.osmonaliev.accountservice.entity.Account;
import com.osmonaliev.accountservice.exsepsion.AccountNotFoundExseption;
import com.osmonaliev.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AccountService {
private final AccountRepository accountRepository;
@Autowired
public AccountService(AccountRepository accountRepository) {
	this.accountRepository = accountRepository;
}

public Account getAccountById(Long id){
	return accountRepository.findById(id).orElseThrow(()->new AccountNotFoundExseption("Uneble to find account with id"+id));
}
public Long createAccount(String name, String email, String phone, List<Long> bills){
	Account account = new Account(name,email,phone, OffsetDateTime.now(),bills);
	return accountRepository.save(account).getAccountId();
}

public Account updateAccount(Long accounId,String name,String email,String phone,List<Long> bills){
	Account account = new Account();
	account.setAccountId(accounId);
	account.setName(name);
	account.setEmail(email);
	account.setPhone(phone);
	account.setBills(bills);
	return accountRepository.save(account);
}
public Account deleteAccount(Long id){
	Account deleteAccount = getAccountById(id);
	accountRepository.deleteById(id);
	return deleteAccount;
}
}

