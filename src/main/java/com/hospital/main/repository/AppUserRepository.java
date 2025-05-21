package com.hospital.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hospital.main.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>,  JpaSpecificationExecutor<AppUser>
{

}
