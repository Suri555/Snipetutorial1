package com.suri.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suri.login.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String name);
	User findByProfile(String profile);

}
