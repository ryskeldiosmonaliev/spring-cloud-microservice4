package com.osmonaliev.billservice.controller;

import com.osmonaliev.billservice.dto.BillRequestDto;
import com.osmonaliev.billservice.dto.BillResponseDto;
import com.osmonaliev.billservice.servic.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BillController {
private final BillService billService;
@Autowired
public BillController(BillService billService) {
	this.billService = billService;
	
}
@GetMapping("/{billId}")
public BillResponseDto getBill(@PathVariable Long billId){
	return new BillResponseDto(billService.getBillById(billId));
}
@PostMapping("/")
public  Long createBill(@RequestBody BillRequestDto billRequestDto){
	return billService.createBill(billRequestDto.getAccountId(),billRequestDto.getAmount(),billRequestDto.getIsDefault(),billRequestDto.getOverdraftEnabled());
}
@PutMapping("/{billId}")
public BillResponseDto updateBill(@PathVariable Long billId,
                                  @RequestBody BillRequestDto billRequestDto ){
	return new  BillResponseDto(billService.updateBill(billId,billRequestDto.getAccountId(),billRequestDto.getAmount(),billRequestDto.getIsDefault(),billRequestDto.getOverdraftEnabled()));
}
@DeleteMapping( "/{billId}")
public BillResponseDto deleteBill(@PathVariable Long billId){
	return  new BillResponseDto(billService.getBillById(billId));
}
}
