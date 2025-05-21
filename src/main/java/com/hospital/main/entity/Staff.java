package com.hospital.main.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String role;
	
	private String email;
	
	private String phone;
	
	@ManyToOne
	@JsonManagedReference // Prevent recursion by managing the relationship in Staff
	private Hospital hospital;
	
	@OneToMany(mappedBy = "staff" , cascade = CascadeType.ALL)
	@JsonManagedReference // Prevent recursion by managing the relationship in Staff
	private List<Patient>patientList;
	
	@OneToOne
	@JoinColumn(name = "app_user_id")
	private AppUser appUser;
	

}
