package com.osmonaliev.depositservice.rest;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class BillRequestDto {
private Long accountId;
private BigDecimal amount;
private Boolean isDefault;
private OffsetDateTime creationDate;
private Boolean overdraftEnabled;

}
