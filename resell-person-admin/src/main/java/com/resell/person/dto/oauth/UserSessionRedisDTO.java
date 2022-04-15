package com.resell.person.dto.oauth;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSessionRedisDTO implements Serializable {

    private String id;
    private String mensagem;
    private String session;
    private String token;
    private String email;
    private String cpf;
    private String userID;
}
