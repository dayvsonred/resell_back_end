package com.resell.person.services;

import com.resell.person.entities.Sales;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SalesService {

    Sales create(Sales obj);

    List<Sales> read();

    Sales update(Sales obj);

    Boolean delete(Long id);
}
