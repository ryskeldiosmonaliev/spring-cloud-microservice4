package com.osmonaliev.depositservice.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
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


}
