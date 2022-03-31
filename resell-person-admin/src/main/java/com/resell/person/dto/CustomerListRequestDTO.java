package com.resell.person.dto;

import com.resell.person.entities.Customer;
import lombok.*;

import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerListRequestDTO {

    private Long person_id;

}
