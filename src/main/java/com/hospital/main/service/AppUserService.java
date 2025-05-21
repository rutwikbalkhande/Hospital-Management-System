package com.hospital.main.service;

import java.util.List;

import com.hospital.main.dto.AppUserDTO;
import com.hospital.main.entity.AppUser;

public interface AppUserService {

	 AppUser save(AppUserDTO appUserDTO);
	
	 AppUser findById(Long id);
	 
	 List<AppUserDTO> findAll(int pageNumber , int pageSize ,String search);
	
	 AppUser upadteUser(Long id, AppUserDTO appUserDTO );
	 
	 void delete(Long id);
}
