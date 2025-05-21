package com.hospital.main.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.hospital.main.entity.Staff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StaffDTO {

    private Long id;
	
	private String name;
	
	private String role;
	
	private String email;
	
	private String phone;
	
	private Long hospitalId;
	
	 private Long appUserId;   // appUser ID to avoid recursion
	 
	 private List<Long> patientIds;  // List of patient IDs associated with the staff
	
	 
	// Constructor using the entity
	 
	public StaffDTO(Staff staff) {
       
		this.id = staff.getId();
		 this.id = staff.getId();
	        this.name = staff.getName();
	        this.role = staff.getRole();
	        this.email = staff.getEmail();
	        this.phone = staff.getPhone();
	      
	        this.hospitalId = staff.getHospital() != null ? staff.getHospital().getId() : null;  // Mapping hospitalId
	      
	        this.appUserId = staff.getAppUser() != null ? staff.getAppUser().getId() : null;  // Mapping appUserId
	     
	        this.patientIds = staff.getPatientList().stream()
	            .map(patient -> patient.getId())  // Mapping patientIds
	            .collect(Collectors.toList());
}
}
