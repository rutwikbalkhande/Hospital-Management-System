package com.hospital.main.dto;

import com.hospital.main.entity.AppUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppUserDTO {

	private Long id;
   
	private String username;
    
	private String password;
    
	private String role;
    
	private boolean active;
	
	private Long staffId;  // Instead of the full Staff object, we just map the staffId

	

	 public AppUserDTO(AppUser appUser) {
		
		this.id = appUser.getId();
		this.username =appUser.getUsername() ;
		this.password =appUser.getPassword();
		this.role = appUser.getRole();
		this.active = appUser.isActive();
		this.staffId = appUser.getStaff() != null ? appUser.getStaff().getId() : null;  // Mapping staffId
	}
	
	
}
