package com.resell.person.services;

import com.resell.person.dto.SalesDTO;
import com.resell.person.entities.Customer;
import com.resell.person.entities.Person;
import com.resell.person.entities.Sales;
import com.resell.person.exception.CustomerException;
import com.resell.person.integration.OauthIntegration;
import com.resell.person.repositories.SalesRepository;
import com.resell.person.util.FormatterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesServiceImpl implements SalesService {

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private OauthService oauthService;

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
                        .price(obj.getPrice())
                        .product(obj.getProduct())
                        .saleDate( FormatterUtils.getDateByString(obj.getSaleDate()) )
                        .dueDate( FormatterUtils.getDateByString(obj.getDueDate()) )
                        .part(obj.getPart())
                        .createDate(LocalDateTime.now())
                        .customer( this.customerServiceImpl.getCustomer(obj.getCustomer()) )
                .build());
    }


    public List<SalesDTO> salesLast(Long customerId) throws CustomerException {
        List<Sales> SalesList = this.salesRepository.findAllByCustomer(Customer.builder().id(customerId).build());
        return SalesList.stream().map(obj -> new SalesDTO(obj)).collect(Collectors.toList());
    }

    public List<SalesDTO> salesLastGetCustomerByToken(Long customerId) throws CustomerException {

        if(customerId.equals(null)){
            System.out.println("e null");
           /// this.oauthService.getUserData()

        }




        return this.salesLast(customerId);
    }



}
