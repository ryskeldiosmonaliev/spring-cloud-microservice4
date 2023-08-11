package com.osmonaliev.accountservice.dto;

import com.osmonaliev.accountservice.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AccountResponseDto {
private Long accountId;
private String name;
private String email;
private List<Long> bills;
private String phone;
private OffsetDateTime creationDate;

public AccountResponseDto(Account account) {
	accountId = account.getAccountId();
	name = account.getName();
	email = account.getEmail();
	phone = account.getPhone();
	creationDate = account.getCreationDate();
	bills = account.getBills();
	
}
}
