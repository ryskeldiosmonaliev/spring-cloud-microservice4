package com.osmonaliev.billservice.servic;

import com.osmonaliev.billservice.entity.Bill;
import com.osmonaliev.billservice.repository.BillRepository;
import com.osmonaliev.billservice.exseption.BillNotFoundExseption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class BillService {
private final BillRepository billRepository;
@Autowired
public BillService(BillRepository billRepository) {
	this.billRepository = billRepository;
}
public Bill getBillById(Long billId){
	return billRepository.findById(billId).orElseThrow(()->new BillNotFoundExseption("Unable to find bill with idL "+billId));
}

public Long createBill(Long accountId, BigDecimal amount, Boolean isDefault,Boolean overdraftEnabled){
Bill bill = new Bill(accountId,amount,isDefault,OffsetDateTime.now(),overdraftEnabled);
return billRepository.save(bill).getBillId();
}

public Bill updateBill(Long billId,Long accountId, BigDecimal amount, Boolean isDefault,Boolean overdraftEnabled){
	Bill bill = new Bill(accountId,amount,isDefault,overdraftEnabled);
	bill.setBillId(billId);
	return billRepository.save(bill);
	
}
public Bill deleteBill(Long billId){
	Bill deleteById = getBillById(billId);
	billRepository.deleteById(billId);
	return deleteById;
}
}
