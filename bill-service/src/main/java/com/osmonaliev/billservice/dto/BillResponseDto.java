package com.osmonaliev.billservice.dto;

import com.osmonaliev.billservice.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
public class BillResponseDto {
private Long billId;
private Long accountId;
private BigDecimal amount;
private Boolean isDefault;
private OffsetDateTime creationDate;
private Boolean overdraftEnabled;

public BillResponseDto(Bill bill) {
	billId = bill.getBillId();
	accountId = bill.getAccountId();
	amount = bill.getAmount();
	isDefault = bill.getIsDefault();
	creationDate = bill.getCreationDate();
	overdraftEnabled = bill.getOverdraftEnabled();
}
}
