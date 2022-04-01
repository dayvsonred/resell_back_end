package com.resell.person.dto;

import com.resell.person.entities.Customer;
import com.resell.person.entities.Person;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDTO {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String phone;
    private String email;
    private String cpf;

    public PersonDTO( Person obj){
        id = obj.getId();
        name = obj.getName();
        phone = obj.getPhone();
        email = obj.getEmail();
        cpf = obj.getCpf();
    }

//    private List<CustomerDTO> addCustomers(List<Customer> customerObj){
//        List<CustomerDTO> customersList = new ArrayList<CustomerDTO>();
//        customerObj.stream().forEach( cust -> customersList.add( new CustomerDTO(cust)));
//        return customersList;
//    }

}
