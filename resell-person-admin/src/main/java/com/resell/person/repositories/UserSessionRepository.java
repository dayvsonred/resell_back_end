package com.resell.person.repositories;

import com.resell.person.entities.session.UserSessionRedis;
import org.springframework.data.repository.CrudRepository;

public interface UserSessionRepository extends CrudRepository<UserSessionRedis, String> {
}
