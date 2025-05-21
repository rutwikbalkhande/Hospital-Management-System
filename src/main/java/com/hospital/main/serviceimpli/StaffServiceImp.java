package com.hospital.main.serviceimpli;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hospital.main.dto.StaffDTO;
import com.hospital.main.entity.AppUser;
import com.hospital.main.entity.Hospital;
import com.hospital.main.entity.Staff;
import com.hospital.main.repository.AppUserRepository;
import com.hospital.main.repository.HospitalRepository;
import com.hospital.main.repository.StaffRepository;
import com.hospital.main.service.StaffService;

import jakarta.persistence.criteria.Predicate;

@Service
public class StaffServiceImp implements StaffService{

	@Autowired
   private	StaffRepository staffRepository;
   
   @Autowired
	private HospitalRepository hospitalRepository;
   
   @Autowired
	 private AppUserRepository appUserRepository;
   
   
	
   @Override
   public Staff save(StaffDTO staffDTO) {
       Staff staff = new Staff();
       
       staff.setId(staffDTO.getId());
       staff.setName(staffDTO.getName());
       staff.setRole(staffDTO.getRole());
       staff.setEmail(staffDTO.getEmail());
       staff.setPhone(staffDTO.getPhone());

       // fetch and set hospital
       Hospital hospital = hospitalRepository.findById(staffDTO.getHospitalId())
               .orElseThrow(() -> new RuntimeException("Hospital not found"));
       staff.setHospital(hospital);

       return staffRepository.save(staff);
   }
   
   @Override
   public List<StaffDTO> findAll(int pageNumber, int pageSize, String search) {

       Pageable pageable = PageRequest.of(pageNumber, pageSize);

       Specification<Staff> spec = (root, query, cb) -> {
           if (search == null || search.trim().isEmpty()) {
               return cb.conjunction(); // No search keyword, return all
           }

           String keyword = "%" + search.toLowerCase() + "%";

           Predicate namePredicate = cb.like(cb.lower(root.get("name")), keyword);
           Predicate emailPredicate = cb.like(cb.lower(root.get("email")), keyword);
           Predicate phonePredicate = cb.like(cb.lower(root.get("phone")), keyword);
           Predicate rolePredicate = cb.like(cb.lower(root.get("role")), keyword);

           return cb.or(namePredicate, emailPredicate, phonePredicate, rolePredicate);
       };

       Page<Staff> staffPage = staffRepository.findAll(spec, pageable);

       return staffPage.getContent()
                       .stream()
                       .map(StaffDTO::new)
                       .collect(Collectors.toList());
   }

/*	
   @Override
	public List<StaffDTO> findAll( int pageNumber, int pageSize , String search) {
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
        Page<Staff> staffPage = staffRepository.findAll( pageable );
           List<Staff> allStaff = staffPage.getContent();
		
	  return allStaff.stream()
			          .map(StaffDTO :: new)
			              .collect(Collectors.toList());
                         
    //     return lists.stream().map(staff -> new StaffDTO (staff ).collect(Collectors.toList());
 
	}
*/
	@Override
	public Staff findById(Long id) {
		
		return staffRepository.findById(id).orElseThrow(() -> new RuntimeException("staff not found"));
	}

	@Override
	public StaffDTO updateStaff(Long id, StaffDTO staffDTO) {
	    return staffRepository.findById(id).map(staff -> {
	        staff.setName(staffDTO.getName());
	        staff.setPhone(staffDTO.getPhone());
	        staff.setEmail(staffDTO.getEmail());
	        staff.setRole(staffDTO.getRole());

	        // Update Hospital
	        Hospital hospital = hospitalRepository.findById(staffDTO.getHospitalId())
	                .orElseThrow(() -> new RuntimeException("Hospital not found"));
	        staff.setHospital(hospital);

	        // Update AppUser
	        if (staffDTO.getAppUserId() != null) {
	            AppUser appUser = appUserRepository.findById(staffDTO.getAppUserId())
	                    .orElseThrow(() -> new RuntimeException("AppUser not found"));
	            staff.setAppUser(appUser);
	        }

	        return new StaffDTO(staffRepository.save(staff));
	    }).orElseThrow(() -> new RuntimeException("Staff not found"));
	}


	@Override
	public void delete(Long id) {
		
		 staffRepository.findById(id).orElseThrow(() -> new RuntimeException("staff not found"));
		
		 staffRepository.deleteById(id);
		 
		
	}

}
