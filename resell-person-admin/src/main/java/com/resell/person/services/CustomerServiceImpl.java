package com.resell.person.services;

import com.resell.person.dto.CustomerDTO;
import com.resell.person.entities.Customer;
import com.resell.person.entities.Person;
import com.resell.person.exception.CustomerException;
import com.resell.person.exception.PersonException;
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

    @Autowired
    private PersonServiceImpl personServiceImpl;

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

    public Customer getCustomer(Long id) throws CustomerException{
        return customerRepository.findById(id).orElseThrow(() -> new CustomerException("Customer_NOT_FOUND"));
    }

    public List<Customer> myCustomers(Long person_id){
        return customerRepository.findAllByPerson(person_id);
    }

    public Customer customerInsert(CustomerDTO obj) throws PersonException {
                    return this.create(Customer.builder()
                    .name(obj.getName())
                    .phone(obj.getPhone())
                    .person( this.personServiceImpl.getPerson(obj.getPerson()) )
                    .build());
    }

}
