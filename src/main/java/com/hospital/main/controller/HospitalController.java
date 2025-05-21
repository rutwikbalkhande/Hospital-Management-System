package com.hospital.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.main.dto.HospitalDTO;
import com.hospital.main.dto.PatientDTO;
import com.hospital.main.entity.Hospital;
import com.hospital.main.service.HospitalService;


@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

	@Autowired
	private HospitalService hospitalservice;
	
	@PostMapping("/create")
	public HospitalDTO create(@RequestBody HospitalDTO hospitalDTO) {
		
		return hospitalservice.create(hospitalDTO);
	}

	@GetMapping("/list")
	public List<HospitalDTO> getAll(@RequestParam( defaultValue = "0" ) int pageNumber ,
			 @RequestParam (defaultValue = "4" ) int pageSize ,
			 @RequestParam(required = false) String search ) 
	{
		
		List<HospitalDTO> allhospital=	 hospitalservice.getAll(pageNumber , pageSize , search );
		 return allhospital;
	}

	@GetMapping("/get-id")
	public Hospital getById(@RequestParam Long id) {
		
		return hospitalservice.getById(id);
	}

	
	@PutMapping("/update")
	public Hospital update(@RequestParam Long id, @RequestBody HospitalDTO hospitalDTO) {
		
		return hospitalservice.update(id,  hospitalDTO);
	}

	@DeleteMapping("/delete")
	public String delete(@RequestParam Long id) {
		
		return hospitalservice.delete(id);
		
		
	}
	
}
