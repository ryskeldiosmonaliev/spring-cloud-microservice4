package com.osmonaliev.accountservice.exsepsion;

public class AccountNotFoundExseption extends RuntimeException{
public AccountNotFoundExseption(String message) {
	super(message);
}
}
