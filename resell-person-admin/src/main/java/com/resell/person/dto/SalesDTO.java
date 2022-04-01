package com.resell.person.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.resell.person.entities.Customer;
import com.resell.person.entities.Sales;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal price;
    private Boolean paid;
    private String saleDate;
    private String dueDate;
    private Long part;
    private LocalDateTime createDate;
    private Long customer;

    public SalesDTO(Sales obj){
        this.id = obj.getId();
        this.price = obj.getPrice();
        this.paid = obj.getPaid();
        this.saleDate = obj.getSaleDate().toString();
        this.dueDate = obj.getDueDate().toString();
        this.part = obj.getPart();
        this.createDate = obj.getCreateDate();
        this.customer = obj.getCustomer().getId();
    }

}
