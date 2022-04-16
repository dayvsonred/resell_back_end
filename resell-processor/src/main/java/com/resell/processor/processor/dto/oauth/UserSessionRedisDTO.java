package com.resell.processor.processor.dto.oauth;

import com.resell.processor.processor.entities.session.UserSessionRedis;
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
    private Long ttl;

    public UserSessionRedisDTO(UserSessionRedis userSessionRedis){
        this.id = userSessionRedis.getId();
        this.session = userSessionRedis.getSession();
        this.token = userSessionRedis.getToken();
        this.email = userSessionRedis.getEmail();
        this.cpf = userSessionRedis.getCpf();
        this.userID = userSessionRedis.getUserID();
        this.ttl = userSessionRedis.getTtl();
    }
}
