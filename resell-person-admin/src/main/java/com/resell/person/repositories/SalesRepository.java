package com.resell.person.repositories;

import com.resell.person.entities.Customer;
import com.resell.person.entities.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    List<Sales> findAllByCustomer(Customer customer);
}
