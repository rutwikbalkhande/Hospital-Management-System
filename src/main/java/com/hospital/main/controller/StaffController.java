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

import com.hospital.main.dto.StaffDTO;
import com.hospital.main.entity.Staff;
import com.hospital.main.service.StaffService;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

	@Autowired
	private StaffService staffService;

	@PostMapping("/create")
	public Staff save(@RequestBody StaffDTO staffDTO) {
		
		return staffService.save(staffDTO);
	}

	@GetMapping("/list")
	public List<StaffDTO> findAll(
			         @RequestParam ( defaultValue = "0", required = false )int pageNumber ,
			         @RequestParam( defaultValue = "4",required = false ) int pageSize ,
			         @RequestParam(required = false) String search ) 
	{
		
		return staffService.findAll(pageNumber, pageSize , search );
	}

	@GetMapping("/get-id")
	public Staff findById(@RequestParam Long id) {
		
		return staffService.findById(id);
	}

	@PutMapping("/update")
	public StaffDTO updateStaff(@RequestParam Long id, @RequestBody StaffDTO staffDTO) {
	
		return staffService. updateStaff( id , staffDTO);
	}

	@DeleteMapping("/delete")
	public void delete( @RequestParam Long id) {

		staffService.delete(id);
		
	}

	
}
