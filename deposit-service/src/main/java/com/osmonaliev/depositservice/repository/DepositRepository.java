package com.osmonaliev.depositservice.repository;

import com.osmonaliev.depositservice.entity.Deposit;
import org.springframework.data.repository.CrudRepository;

public interface DepositRepository  extends CrudRepository<Deposit,Long> {
}
