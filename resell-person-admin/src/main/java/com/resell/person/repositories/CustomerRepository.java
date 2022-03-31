package com.resell.person.repositories;

import com.resell.person.entities.Customer;
import com.resell.person.entities.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //@Cacheable("FeatureRecordingRepository.findAllByStatus")
    List<Customer> findAllByPerson(Long person);
}
