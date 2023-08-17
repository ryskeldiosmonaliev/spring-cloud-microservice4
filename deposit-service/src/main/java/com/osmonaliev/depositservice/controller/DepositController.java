package com.osmonaliev.depositservice.controller;

import com.osmonaliev.depositservice.dto.DepositRequestDTO;
import com.osmonaliev.depositservice.dto.DepositResponseDTO;
import com.osmonaliev.depositservice.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositController {
    private final DepositService depositService;

    @Autowired
    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/deposits")
    public DepositResponseDTO deposit(@RequestBody DepositRequestDTO depositRequestDTO) {

        return depositService.deposit(depositRequestDTO.getAccountId(),depositRequestDTO.getBillId(),depositRequestDTO.getAmount());
    }
}
