package com.osmonaliev.depositservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osmonaliev.depositservice.dto.DepositResponseDTO;
import com.osmonaliev.depositservice.entity.Deposit;
import com.osmonaliev.depositservice.exseption.DepositSerivceExseption;
import com.osmonaliev.depositservice.repository.DepositRepository;
import com.osmonaliev.depositservice.rest.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class DepositService {
    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";
    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";
    private final DepositRepository depositRepository;

    private final AccountServiceClient accountServiceClient;

    private final BillServiceClient billServiceClient;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public DepositService(DepositRepository depositRepository, AccountServiceClient accountServiceClient, BillServiceClient billServiceClient, RabbitTemplate rabbitTemplate) {
        this.depositRepository = depositRepository;
        this.accountServiceClient = accountServiceClient;
        this.billServiceClient = billServiceClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    public DepositResponseDTO deposit(Long accountId, Long billId, BigDecimal amount) {
        if (accountId == null && billId == null) {
            throw new DepositSerivceExseption("Account is null and bill is null");
        }
        if (billId != null) {
            BillResponseDto billResponseDto = billServiceClient.getBillById(billId);
            BillRequestDto billRequestDto = creatBillRequest(amount, billResponseDto);

            billServiceClient.update(billId, billRequestDto);

            AccountResponseDto accountResponseDto = accountServiceClient.getAccountById(billResponseDto.getAccountId());

            depositRepository.save(new Deposit(amount, billId, OffsetDateTime.now(), accountResponseDto.getEmail()));
            return creataBillReuest(amount, accountResponseDto);
        }
        BillResponseDto defaulBill = getDefaulBill(accountId);
        BillRequestDto billRequestDto = creatBillRequest(amount, defaulBill);
        billServiceClient.update(defaulBill.getBillId(), billRequestDto);
        AccountResponseDto account = accountServiceClient.getAccountById(accountId);
        depositRepository.save(new Deposit(amount, defaulBill.getBillId(), OffsetDateTime.now(), account.getEmail()));
        return creataBillReuest(amount, account);
    }

    private DepositResponseDTO creataBillReuest(BigDecimal amount, AccountResponseDto account) {
        DepositResponseDTO depositResponseDTO = new DepositResponseDTO(amount, account.getEmail());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rabbitTemplate.convertAndSend(TOPIC_EXCHANGE_DEPOSIT, ROUTING_KEY_DEPOSIT,
                    objectMapper.writeValueAsString(depositResponseDTO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new DepositSerivceExseption("Can't send message to RabbitMQ");
        }
        return depositResponseDTO;
    }

    private static BillRequestDto creatBillRequest(BigDecimal amount, BillResponseDto defaulBill) {
        BillRequestDto billRequestDto = new BillRequestDto();
        billRequestDto.setAccountId(defaulBill.getAccountId());
        billRequestDto.setCreationDate(defaulBill.getCreationDate());
        billRequestDto.setIsDefault(defaulBill.getIsDefault());
        billRequestDto.setOverdraftEnabled(defaulBill.getOverdraftEnabled());
        billRequestDto.setAmount(defaulBill.getAmount().add(amount));
        return billRequestDto;
    }


    private BillResponseDto getDefaulBill(Long accountId) {
        return billServiceClient.getBillsByAccountId(accountId).stream()
                .filter(BillResponseDto::getIsDefault)
                .findAny()
                .orElseThrow(() -> new DepositSerivceExseption("Unable to find bill to account " + accountId));
    }
}
