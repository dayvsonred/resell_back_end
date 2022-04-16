package com.resell.processor.processor.repositories;

import com.resell.processor.processor.entities.session.UserSessionRedis;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserSessionRepository  extends CrudRepository<UserSessionRedis, String> {

    Optional<UserSessionRedis> findByEmail(String email);

}