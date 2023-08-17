package com.osmonaliev.billservice.repository;

import com.osmonaliev.billservice.entity.Bill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillRepository extends CrudRepository<Bill,Long> {
    List<Bill> getBillsByAccountId(Long accountId);
}
