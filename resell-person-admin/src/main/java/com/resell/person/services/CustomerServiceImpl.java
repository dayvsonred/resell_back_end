package com.resell.person.services;

import com.resell.person.dto.CustomerDTO;
import com.resell.person.dto.oauth.UserTokenDTO;
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

    @Autowired
    private OauthService oauthService;

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
        return customerRepository.saveAndFlush(obj);
    }

    @Override
    public Boolean delete(Long id) {
        customerRepository.deleteById(id);
        return true;
    }

    public Customer getCustomer(Long id) throws CustomerException{
        return this.customerRepository.findById(id).orElseThrow(() -> new CustomerException("Customer_NOT_FOUND"));
    }

    public List<Customer> myCustomers(Long person_id){
        return this.customerRepository.findAllByPerson(Person.builder().id(person_id).build());
    }

    public Customer customerInsert(String token, CustomerDTO obj) throws PersonException {
        UserTokenDTO userTokenDTO = this.oauthService.getUserData(token);

                    return this.create(Customer.builder()
                            .name(obj.getName())
                            .phone(obj.getPhone())
                            .email(obj.getEmail())
                            .person( this.personServiceImpl.getPerson(userTokenDTO.getId()) )
                            .build());
    }

    public void customerDell(String token, Long customerId) throws RuntimeException {
        try {
            UserTokenDTO userTokenDTO = this.oauthService.getUserData(token);

            Customer customer = this.customerRepository.findById(customerId).orElseThrow(
                    () -> new RuntimeException("ERROR FIND CUSTOMER customerDell"));

            if(customer.getPerson().getId().equals(userTokenDTO.getId())){
                this.customerRepository.deleteById(customer.getId());
            }
        }catch (Exception e){
            log.error("ERROR FIND CUSTOMER customerDell");
            throw new RuntimeException("ERROR FIND CUSTOMER customerDell");
        }
    }

    public Customer customerEdit(String token, CustomerDTO obj) throws CustomerException {
        try {
            UserTokenDTO userTokenDTO = this.oauthService.getUserData(token);

            log.info("-*-------*-*-*----*-*-*---*-*--*-*");
            log.info(obj.getId().toString());


            if(obj.getPerson().equals(userTokenDTO.getId())){

                Customer customer = this.getCustomer(obj.getId());
                customer.setName(obj.getName());
                customer.setEmail(obj.getEmail());
                customer.setPhone(obj.getPhone());
                return this.update(customer);
            }
            throw new CustomerException("ERROR CHECK UPDATE CUSTOMER NOT THIS USER customerEdit" + obj.getId());
        }catch (Exception e){
         log.info("ERROR UPDATE CUSTOMER customerEdit" + obj.getId() );
         throw new CustomerException("ERROR UPDATE CUSTOMER customerEdit" + obj.getId());
        }
    }
}
