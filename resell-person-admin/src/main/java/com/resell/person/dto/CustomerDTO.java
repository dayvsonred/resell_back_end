package com.resell.person.dto;

import com.resell.person.entities.Customer;
import com.resell.person.entities.Person;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String phone;
    private String email;
    private Long person;

    public CustomerDTO(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.person = customer.getPerson().getId();
    }
}
