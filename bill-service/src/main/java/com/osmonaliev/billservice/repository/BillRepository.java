package com.osmonaliev.billservice.repository;

import com.osmonaliev.billservice.entity.Bill;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bill,Long> {
}
