package com.resell.processor.processor.dto.getnet;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthGetNetDTO implements Serializable {

    private String access_token;
    private String token_type;
    private String expires_in;
    private String scope;
}
