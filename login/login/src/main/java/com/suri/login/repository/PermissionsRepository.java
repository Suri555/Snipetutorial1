package com.suri.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suri.login.model.Permissions;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> 
{
	Permissions findByName(String name);

}
