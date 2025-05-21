package com.hospital.main.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
   
	private String username;
    
	private String password;
    
	private String role;
    
	private boolean active;
	
	@OneToOne(mappedBy = "appUser", cascade = CascadeType.ALL)
	@JsonBackReference
    private Staff staff;

	
	
}
