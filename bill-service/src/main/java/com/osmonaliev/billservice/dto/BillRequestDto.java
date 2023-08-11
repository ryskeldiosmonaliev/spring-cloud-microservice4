package com.osmonaliev.billservice.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Getter
public class BillRequestDto {
private Long accountId;
private BigDecimal amount;
private Boolean isDefault;
private OffsetDateTime creationDate;
private Boolean overdraftEnabled;

}
