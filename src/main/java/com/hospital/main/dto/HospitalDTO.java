package com.hospital.main.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.hospital.main.entity.Hospital;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HospitalDTO {

    private Long id;
	
	private String name;
	
	private String location;
	
	private String phoneNumber;
	
	private List<Long> staffIds;  // List of staff IDs
	
    private List<Long> patientIds;  // List of patient IDs
	
    // Constructor using the entity
    
	public HospitalDTO(Hospital hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.location = hospital.getLocation();
        this.phoneNumber = hospital.getPhoneNumber();
        
                  // Map staff and patient objects to their IDs
        this.staffIds = hospital.getStaffList().stream()
            .map(staff -> staff.getId())
            .collect(Collectors.toList());
        
        this.patientIds = hospital.getPatientList().stream()
            .map(patient -> patient.getId())
            .collect(Collectors.toList());
    }
	
	
}
