package com.resell.person.services;


import com.resell.person.entities.session.UserSessionRedis;
import com.resell.person.repositories.UserSessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserSessionService {

    private final UserSessionRepository userSessionRepository;

    public void saveSession(UserSessionRedis userSessionRedis){
        userSessionRepository.save(userSessionRedis);
    }

}
