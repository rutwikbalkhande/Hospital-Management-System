package com.hospital.main.service;

import java.util.List;

import com.hospital.main.dto.PatientDTO;
import com.hospital.main.entity.Patient;

public interface PatientService {

	Patient create(PatientDTO patientDTO);
	
//	List<PatientDTO> findAll(int pageNumber,int pageSize );
	
	List<PatientDTO> findAll(int pageNumber, int pageSize , String search);  // use for pageList & search using criteriaBuilder 
	
	Patient findById(Long id);

	Patient updatePatient(Long id, PatientDTO patientDTO );
	
	void delete(Long id);
	
	
	/*   postman dat ato create api
	 {
  "name": "Alice Smith",
  "age": "28",
  "disease": "Pneumonia",
  "contact": "9876543210",
  "staffId": 1,
  "hospitalId": 2
}

	 */
}
