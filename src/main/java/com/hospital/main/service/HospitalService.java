package com.hospital.main.service;

import java.util.List;

import org.aspectj.apache.bcel.generic.LineNumberGen;

import com.hospital.main.dto.HospitalDTO;
import com.hospital.main.entity.Hospital;

public interface HospitalService {

	HospitalDTO create(HospitalDTO hospitalDTO);
	
	List <HospitalDTO> getAll(int pageNumber , int pageSize , String search);
	
	Hospital getById(Long Id);
	
	Hospital update(Long id, HospitalDTO hospitalDTO);
    
	String delete(Long id);
	
	
}
