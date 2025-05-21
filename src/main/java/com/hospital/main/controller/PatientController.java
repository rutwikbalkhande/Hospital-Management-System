package com.hospital.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.main.dto.PatientDTO;
import com.hospital.main.entity.Patient;
import com.hospital.main.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	private PatientService patienservice;

	@PostMapping("/create")
	public Patient create(@RequestBody PatientDTO patientDTO) {
		
		return patienservice.create(patientDTO);
	}

	@GetMapping("/list")
	public ResponseEntity<List<PatientDTO>> findAll( 
			 @RequestParam ( defaultValue = "0", required = false )int pageNumber ,
	         @RequestParam( defaultValue = "3",required = false ) int pageSize ,
	         @RequestParam(required = false) String search ) 
		
	{     		
		List<PatientDTO> allPatient = patienservice.findAll(pageNumber, pageSize , search );
	    
	      return ResponseEntity.ok(allPatient);
	}


	@GetMapping("find-id")
	public Patient findById( @RequestParam Long id) {
		
		return patienservice.findById(id);
	}

	@PutMapping("/update")                                   //postman use param to pass id 
	public Patient updatePatient( @RequestParam Long id, @RequestBody PatientDTO patientDTO) {
		
		return patienservice.updatePatient( id,patientDTO);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam Long id) {
		
		patienservice.delete(id);
		
		
	}


}
