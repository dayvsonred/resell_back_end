package com.resell.person.services;

import com.resell.person.entities.Customer;
import com.resell.person.repositories.CustomerRepository;
import com.resell.person.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer create(Customer obj) {
        return customerRepository.save(obj);
    }

    @Override
    public List<Customer> read() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Customer obj) {
        return customerRepository.save(obj);
    }

    @Override
    public Boolean delete(Long id) {
        customerRepository.deleteById(id);
        return true;
    }

    public List<Customer> myCustomers(Long person_id){
        return customerRepository.findAllByPerson(person_id);
    }

}
