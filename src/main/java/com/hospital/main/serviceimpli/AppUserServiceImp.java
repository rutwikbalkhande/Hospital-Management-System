package com.hospital.main.serviceimpli;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hospital.main.dto.AppUserDTO;
import com.hospital.main.dto.HospitalDTO;
import com.hospital.main.entity.AppUser;
import com.hospital.main.repository.AppUserRepository;
import com.hospital.main.service.AppUserService;

import jakarta.persistence.criteria.Predicate;

@Service
public class AppUserServiceImp implements AppUserService {

	@Autowired
	 private AppUserRepository appUserRepository;
	
	@Override
	public AppUser save(AppUserDTO appUserDTO) {
		
		AppUser appUser =new AppUser();
		
		appUser.setUsername(appUserDTO.getUsername());
		appUser.setPassword(appUserDTO.getPassword());
		appUser.setRole(appUserDTO.getRole());
		
		return appUserRepository.save(appUser);
			
	}

	@Override
	public AppUser findById(Long id) {
		
		return appUserRepository.findById(id).orElseThrow(() ->new RuntimeException("user not found"));
	
	}
	
	@Override
	public List<AppUserDTO> findAll(int pageNumber, int pageSize, String search) {

	    Pageable pageable = PageRequest.of(pageNumber, pageSize);

	    Specification<AppUser> spec = (root, query, cb) -> {
	        if (search == null || search.trim().isEmpty()) {
	            return cb.conjunction();  // return all if no search
	        }

	        String keyword = search.toLowerCase() + "%";

	        Predicate usernamePredicate = cb.like(cb.lower(root.get("username")), keyword);
	        Predicate rolePredicate = cb.like(cb.lower(root.get("role")), keyword);

	        return cb.or(usernamePredicate, rolePredicate);  // either username or role matches
	    };

	    Page<AppUser> appUserPage = appUserRepository.findAll(spec, pageable);

	    return appUserPage.getContent()
	                      .stream()
	                      .map(AppUserDTO::new)
	                      .collect(Collectors.toList());
	}

/*
	@Override
	public List<AppUserDTO> findAll(int pageNumber , int pageSize , String search) {
		
	 Pageable pageable   = PageRequest.of(pageNumber, pageSize);
		
		Page <AppUser> appUserPage = appUserRepository.findAll(pageable);
		
		List<AppUser> appUsersList = appUserPage.getContent();
		 
		
		return appUsersList .stream().map(appUser -> new AppUserDTO(appUser)).collect(Collectors.toList()) ;
		
	}
*/
	@Override
	public AppUser upadteUser(Long id, AppUserDTO appUserDTO) {
		
		return appUserRepository.findById(id). map ( appUser -> {
			
			appUser.setUsername(appUserDTO.getUsername());
			appUser.setPassword(appUserDTO.getPassword());
			appUser.setRole(appUserDTO.getRole());
		//	appUser.setActive(appUserDTO.isActive());
			  
			return appUserRepository.save(appUser);
		//	  return "user details update succesfully !";
		
			
		}).orElseThrow(() -> new RuntimeException("user not found by id :" + id));

	}

	@Override
	public void delete(Long id) {
		
		appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found by id: " + id));
	    appUserRepository.deleteById(id);
		
	
		
	}

}
