package com.resell.processor.processor.dto;

import com.resell.processor.processor.entities.session.UserSessionRedis;
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

    public UserSessionRedisDTO(UserSessionRedis userSessionRedis){
        this.id = userSessionRedis.getId();
        this.session = userSessionRedis.getSession();
        this.token = userSessionRedis.getToken();
        this.email = userSessionRedis.getEmail();
        this.cpf = userSessionRedis.getCpf();
        this.userID = userSessionRedis.getUserID();
    }
}
