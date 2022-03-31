package com.resell.person.services;

import com.resell.person.dto.SalesDTO;
import com.resell.person.entities.Sales;
import com.resell.person.exception.CustomerException;
import com.resell.person.repositories.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private SalesRepository salesRepository;

    @Override
    public Sales create(Sales obj) {
        return this.salesRepository.save(obj);
    }

    @Override
    public List<Sales> read() {
        return this.salesRepository.findAll();
    }

    @Override
    public Sales update(Sales obj) {
        return this.salesRepository.save(obj);
    }

    @Override
    public Boolean delete(Long id) {
        this.salesRepository.deleteById(id);
        return true;
    }

    public Sales insert(SalesDTO obj) throws CustomerException {
        return this.create(Sales.builder()
                        .paid(obj.getPaid())
                        .saleDate(obj.getSaleDate())
                        .dueDate(obj.getDueDate())
                        .part(obj.getPart())
                        .createDate(LocalDateTime.now())
                        .customer( this.customerServiceImpl.getCustomer(obj.getCustomer()) )
                .build());
    }
}
