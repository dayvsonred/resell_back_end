package com.resell.person.entities.session;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.TimeToLive;


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

    @TimeToLive
    private Long ttl;

}
