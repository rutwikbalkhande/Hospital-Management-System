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

import com.hospital.main.dto.AppUserDTO;
import com.hospital.main.entity.AppUser;
import com.hospital.main.repository.AppUserRepository;
import com.hospital.main.service.AppUserService;

@RestController
@RequestMapping("api/appuser")
public class AppUserController {

	@Autowired
	private AppUserService appservice;
	
	@PostMapping("/create")
	public AppUser save( @RequestBody AppUserDTO appUserDTO) {
		
		return appservice.save(appUserDTO);
	}

	@GetMapping("/get-id")
	public AppUser findById(@RequestParam Long id) {
		
		return appservice.findById(id);
	}

	@GetMapping("/list")
	public List<AppUserDTO> findAll(@RequestParam (defaultValue = "0") int pageNumber ,
			@RequestParam (defaultValue = "5") int pageSize , 
			@RequestParam(required = false) String search) 
	{
		 
		return appservice.findAll(pageNumber , pageSize , search) ;
	}

	@PutMapping("/update")
	public AppUser upadteUser(@RequestParam Long id, @RequestBody AppUserDTO appUserDTO) {
		
		return appservice.upadteUser( id, appUserDTO);
	}

	@DeleteMapping("/delete")
	public void delete(@RequestParam Long id) {
		
		appservice.delete(id);
	}
	
}
