package com.resell.processor.processor.dto.getnet;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthGetNetRequestDTO implements Serializable {

    private String client_id;
    private String client_secret;
    private String authorization;
    private String authorizationType;
}
