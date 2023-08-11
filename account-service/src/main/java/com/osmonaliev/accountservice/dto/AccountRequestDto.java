package com.osmonaliev.accountservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class AccountRequestDto {

private String name;
private String email;
private String phone;
private List<Long> bills;
private OffsetDateTime creationDate;
}
