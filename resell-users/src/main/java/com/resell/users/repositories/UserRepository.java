package com.resell.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.resell.users.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
