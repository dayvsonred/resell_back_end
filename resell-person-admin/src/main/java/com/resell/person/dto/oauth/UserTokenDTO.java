package com.resell.person.dto.oauth;


import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTokenDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();

}
