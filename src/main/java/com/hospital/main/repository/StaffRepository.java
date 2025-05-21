package com.hospital.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.hospital.main.entity.Hospital;
import com.hospital.main.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long>, JpaSpecificationExecutor<Staff>
 {

}
