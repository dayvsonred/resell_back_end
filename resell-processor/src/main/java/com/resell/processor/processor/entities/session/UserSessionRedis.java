package com.resell.processor.processor.entities.session;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("userSession")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSessionRedis {

    @Id
    private String id;
    private String mensagem;
    private String session;
    private String token;
    private String email;
    private String cpf;
    private String userID;

}
