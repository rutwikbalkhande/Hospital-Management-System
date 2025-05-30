package com.hospital.main.entity;

import java.util.List;

import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String location;
	
	private String phoneNumber;
	
	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Staff> staffList;
	
	@OneToMany(mappedBy ="hospital", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Patient> patientList;
	
	

}
