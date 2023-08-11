package com.osmonaliev.accountservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Account {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long accountId;
private String name;
private String email;
private String phone;
private OffsetDateTime creationDate;
@ElementCollection
private List<Long> bills;

public Account(String name, String email, String phone, OffsetDateTime creationDate, List<Long> bills) {
	this.accountId = accountId;
	this.name = name;
	this.email = email;
	this.phone = phone;
	this.creationDate = creationDate;
	this.bills = bills;
}
public Account(){}
}
