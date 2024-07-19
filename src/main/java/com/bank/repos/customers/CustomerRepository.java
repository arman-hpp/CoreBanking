package com.bank.repos.customers;

import com.bank.models.customers.Customer;
import org.springframework.stereotype.Repository;
import com.bank.repos.BaseRepository;

import java.util.List;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long> {
    List<Customer> findAllByOrderByIdDesc();
}
