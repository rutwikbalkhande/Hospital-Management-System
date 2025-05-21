package com.hospital.main.service;

import java.util.List;

import com.hospital.main.dto.StaffDTO;
import com.hospital.main.entity.Staff;

public interface StaffService {

	Staff save(StaffDTO staffDTO);
	
	List<StaffDTO> findAll(int pageNumber, int pageSize , String search);
	
	Staff findById(Long id);
	
	StaffDTO updateStaff(Long id, StaffDTO staffDTO );
	
	void delete(Long id);
	
}
