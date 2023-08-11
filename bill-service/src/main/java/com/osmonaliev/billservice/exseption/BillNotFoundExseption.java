package com.osmonaliev.billservice.exseption;

public class BillNotFoundExseption extends RuntimeException{
public BillNotFoundExseption(String message) {
	super(message);
}
}
