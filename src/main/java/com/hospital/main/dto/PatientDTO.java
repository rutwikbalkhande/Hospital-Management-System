package com.hospital.main.dto;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;

import com.hospital.main.entity.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientDTO {

    private Long id;
	
	private String name;
	
	private String age;
	
	private String disease;
	
	private String contact;
	
	private Long staffId;    //  avoid recursion
	
	private Long hospitalId;   // hospital ID to avoid recursion
	
	 
	  
	
	public PatientDTO(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.age = patient.getAge();
        this.disease = patient.getDisease();
        this.contact = patient.getContact();
        this.hospitalId = patient.getHospital().getId();
        this.staffId = patient.getStaff().getId();
    }
}
