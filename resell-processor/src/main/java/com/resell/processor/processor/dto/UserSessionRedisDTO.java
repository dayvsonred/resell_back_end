package com.resell.processor.processor.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSessionRedisDTO {

    private String id;
    private String mensagem;
    private String session;
    private String token;
    private String email;
    private String cpf;
    private String userID;
}
