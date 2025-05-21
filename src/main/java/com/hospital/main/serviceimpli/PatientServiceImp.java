package com.hospital.main.serviceimpli;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;


import com.hospital.main.dto.PatientDTO;
import com.hospital.main.entity.Hospital;
import com.hospital.main.entity.Patient;
import com.hospital.main.entity.Staff;
import com.hospital.main.repository.HospitalRepository;
import com.hospital.main.repository.PatientRepository;
import com.hospital.main.repository.StaffRepository;
import com.hospital.main.service.PatientService;

import jakarta.persistence.criteria.Predicate;

@Service
public class PatientServiceImp implements PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	   private	StaffRepository staffRepository;
	
	@Override
	public Patient create(PatientDTO patientDTO) {
		
		Patient patient = new Patient();
		
		patient.setName(patientDTO.getName());
		patient.setAge(patientDTO.getAge());
		patient.setContact(patientDTO.getContact());
		patient.setDisease(patientDTO.getDisease());
		
		// fetch and set hospital
	    Hospital hospital = hospitalRepository.findById(patientDTO.getHospitalId())
	            .orElseThrow(() -> new RuntimeException("Hospital not found with ID: " + patientDTO.getHospitalId()));
	    patient.setHospital(hospital);

	    // fetch and set staff
	    Staff staff = staffRepository.findById(patientDTO.getStaffId())
	            .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + patientDTO.getStaffId()));
	    patient.setStaff(staff);

		
		return patientRepository.save(patient);
	}
	
	

	@Override
	public List<PatientDTO> findAll(int pageNumber, int pageSize, String search) {

	    Pageable pageable = PageRequest.of(pageNumber, pageSize);

	    Specification<Patient> spec = (root, query, cb) -> {  // cb= criteriaBuilder create "where conditions"
	      
	    	if (search == null ||search.trim().isEmpty())      // trim() remove space
	    	{      
	    		return cb.conjunction();      // it return true if not search any keyword & return all records
	        }

	        String searchKeyword =  search.toLowerCase() + "%";          //%abc% का मतलब होता है कि "abc" शब्द कहीं भी हो in between starting or ending

	        Predicate namePredicate = cb.like(cb.lower(root.get("name")), searchKeyword );
	        Predicate diseasePredicate = cb.like(cb.lower(root.get("disease")), searchKeyword );
	        Predicate contactPredicate = cb.like(cb.lower(root.get("contact")), searchKeyword );  //to search word it check all 3 field "name,"disease","contact"  

	        return cb.or(namePredicate, diseasePredicate, contactPredicate);
	    };

	    Page<Patient> page = patientRepository.findAll(spec, pageable);

	    return page.getContent()
	               .stream()
	               .map(PatientDTO::new)
	               .collect(Collectors.toList());
	}



/*	
	@Override
	public List<PatientDTO> findAll(int pageNumber, int pageSize ) {
	    
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
	   
		Page<Patient> patientPage = patientRepository.findAll(pageable);
	  
	    List<Patient> allPatients = patientPage.getContent();
	    
	    return allPatients.stream()
	            .map( PatientDTO :: new )
	            .collect(Collectors.toList());
	}
*/
	
	@Override
	public Patient findById(Long id) {
		
	  return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("patient not find id: "+id));
	}


	
	@Override
	public Patient updatePatient(Long id, PatientDTO patientDTO) {                   
		return patientRepository.findById(id).map(patient -> {
			
			patient.setName(patientDTO.getName());
			patient.setAge(patientDTO.getAge());
			patient.setContact(patientDTO.getContact());
			patient.setDisease(patientDTO.getDisease());
			
			 // Set hospital only if provided
		    if (patientDTO.getHospitalId() != null) {
		        Hospital hospital = hospitalRepository.findById(patientDTO.getHospitalId())
		            .orElseThrow(() -> new RuntimeException("Hospital not found with ID: " + patientDTO.getHospitalId()));
		        patient.setHospital(hospital);
		    }

		    // Set staff only if provided
		    if (patientDTO.getStaffId() != null) {
		        Staff staff = staffRepository.findById(patientDTO.getStaffId())
		            .orElseThrow(() -> new RuntimeException("Staff not found with ID: " + patientDTO.getStaffId()));
		        patient.setStaff(staff);
		    }
			return	patientRepository.save(patient);
			 	
		}).orElseThrow(() -> new RuntimeException("user not found"));
		
	}

	
	
	@Override
	public void delete(Long id) {
		patientRepository.deleteById(id);
		
	}

}

/* findAll
root: यह entity (Patient) की root reference को दर्शाता है।

query: यह JPA CriteriaQuery को दर्शाता है (आपका SELECT query)।

cb: यह CriteriaBuilder है — जो dynamic predicates (where conditions) बनाने के लिए methods प्रदान करता है।

*/
