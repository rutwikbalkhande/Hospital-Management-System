package com.hospital.main.serviceimpli;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hospital.main.dto.HospitalDTO;
import com.hospital.main.entity.Hospital;
import com.hospital.main.repository.HospitalRepository;
import com.hospital.main.service.HospitalService;

import jakarta.persistence.criteria.Predicate;

@Service
public class HospitalServiceImp implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;
	
       private HospitalDTO maptoDTO(Hospital h) {   //converting  entity to dto 
		
		HospitalDTO dto=new HospitalDTO(); 
		
		dto.setId(h.getId());
		dto.setName(h.getName());
		dto.setPhoneNumber(h.getPhoneNumber());
		dto.setLocation(h.getLocation());
			
		return dto;
		
	}
	
	@Override
	public HospitalDTO create(HospitalDTO hospitalDTO) {
		
		Hospital hospital=new Hospital();
		
		hospital.setId(hospitalDTO.getId());
		hospital.setName(hospitalDTO.getName());
		hospital.setPhoneNumber(hospitalDTO.getPhoneNumber());
		hospital.setLocation(hospitalDTO.getLocation());
			
		Hospital savedHospital = hospitalRepository.save(hospital); // Save entity first
	    return new HospitalDTO(savedHospital); // Convert saved entity to DTO
				 
	}
	
	
	@Override
	public List<HospitalDTO> getAll(int pageNumber, int pageSize, String search) {
	    
	    Pageable pageable = PageRequest.of(pageNumber, pageSize);

	    Specification<Hospital> spec = (root, query, cb) -> {
	        if (search == null || search.trim().isEmpty()) {
	            return cb.conjunction(); // सभी records लाने के लिए
	        }

	        String keyword = "%" + search.toLowerCase() + "%";

	        Predicate namePredicate = cb.like(cb.lower(root.get("name")), keyword);
	        Predicate locationPredicate = cb.like(cb.lower(root.get("location")), keyword);
	        Predicate phonePredicate = cb.like(cb.lower(root.get("phoneNumber")), keyword);

	        return cb.or(namePredicate, locationPredicate, phonePredicate); // return matdhing record
	    };

	    Page<Hospital> hospitalPage = hospitalRepository.findAll(spec,pageable);

	    return hospitalPage.getContent()
	            .stream()
	            .map(HospitalDTO::new)
	            .collect(Collectors.toList());
	}

	/*       return hospitals.stream()
	    		     .map(hospital -> new HospitalDTO(hospital))
	    		         .collect(Collectors.toList())  ;    
    */	    		         
	
	

	@Override
	public Hospital getById(Long id) {
		
		return hospitalRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found by id :" + id));
	}

	@Override
	public Hospital update(Long id, HospitalDTO hospitalDTO) {
		
	return hospitalRepository.findById(id). map ( hospital -> {
		
		  hospital.setName(hospitalDTO.getName());
		  hospital.setPhoneNumber(hospitalDTO.getPhoneNumber());
		  hospital.setLocation(hospitalDTO.getLocation());
		  
		return  hospitalRepository.save(hospital);
	//	  return "user details update succesfully !";
	
		
	}).orElseThrow(() -> new RuntimeException("user not found by id :" + id));

	}

	
	@Override
	public String delete(Long id) {
		
		hospitalRepository.findById(id).orElseThrow(() -> new RuntimeException(" not found by id: " + id));
		
		hospitalRepository.deleteById(id);
		
		return " record delete succesfully ! ";
		
		
	}

	
	
}