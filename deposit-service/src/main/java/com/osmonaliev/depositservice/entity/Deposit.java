package com.osmonaliev.depositservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long depositId;
    private BigDecimal amount;
    private Long billId;
    private OffsetDateTime creationData;
    private String email;

    public Deposit(BigDecimal amount, Long billId, OffsetDateTime creationData, String email) {
        this.amount = amount;
        this.billId = billId;
        this.creationData = creationData;
        this.email = email;
    }
}
