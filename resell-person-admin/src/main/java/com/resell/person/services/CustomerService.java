package com.resell.person.services;

import com.resell.person.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    Customer create(Customer obj);

    List<Customer> read();

    Customer update(Customer obj);

    Boolean delete(Long id);
}
