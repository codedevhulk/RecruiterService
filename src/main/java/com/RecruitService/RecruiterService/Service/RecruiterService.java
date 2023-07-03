package com.RecruitService.RecruiterService.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.RecruitService.RecruiterService.JwtUtils;
import com.RecruitService.RecruiterService.Entity.RecruiterDetails;
import com.RecruitService.RecruiterService.Exception.CustomException;
import com.RecruitService.RecruiterService.Model.RecruiterSignupResponse;
import com.RecruitService.RecruiterService.Model.SignInDetailsRequest;
import com.RecruitService.RecruiterService.Model.UserInfoResponse;
import com.RecruitService.RecruiterService.Repository.RecruiterRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecruiterService {
	
	@Autowired
	RecruiterRepo recruiterRepo;
	@Autowired
	JwtUtils jwtUtils;
	//@Autowired
	//AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public RecruiterSignupResponse createNewRecruiter(RecruiterDetails rd) {
		
		RecruiterDetails recruiterDetails=recruiterRepo.findByUsername(rd.getUsername());
		if(!Objects.isNull(recruiterDetails)) {	 
			throw new CustomException("Try with other username", "", 500);
		}
		
		rd.setPassword(passwordEncoder.encode(rd.getPassword()));
		RecruiterDetails obj=recruiterRepo.save(rd);
		return new RecruiterSignupResponse(obj.getRecruiterId(),"Recruiter successfully registered");
	}
	public RecruiterDetails getRecruiterDetails(long id) {
		return recruiterRepo.findById(id).get();
	}
	public RecruiterDetails updateRecruiterDetails(RecruiterDetails rd) {
		
		return recruiterRepo.save(rd);
	}
	/*public boolean deleteRecruiterDetails(int id) {
		rrepo.deleteById(id);
		return null;
	}*/
	
	
	
	
	
	
	
	
	
	
	// Rajesh
	
	
	
	// To check the recruiter whether he exists or not, if exists then update the details, If not just save the new details
	
/*public void signupDetails(RecruiterDetails recruiterDetails) {
		
		
		
		
	RecruiterDetails existingRecruiterDetails=recruiterRepo.findByUserName(recruiterDetails.getUserName());
		
		//log.info(jobSeekerDetails.toString());
		
		//updating the Jobseekerdetails
		
		
		if(Objects.nonNull(existingRecruiterDetails)) {
			
			
			
			if(Objects.nonNull(recruiterDetails.getRecruiterId())) {
				
				
				
				existingRecruiterDetails.setRecruiterId(recruiterDetails.getRecruiterId());
			}
			
			
			
			
			if(Objects.nonNull(recruiterDetails.getFirstName())) {
				existingRecruiterDetails.setFirstName(recruiterDetails.getFirstName());
			}
			if(Objects.nonNull(recruiterDetails.getLastName())) {
				existingRecruiterDetails.setLastName(recruiterDetails.getLastName());
			}
			if(Objects.nonNull(recruiterDetails.getUserName())) {
				existingRecruiterDetails.setUserName(recruiterDetails.getUserName());
			}
			if(Objects.nonNull(recruiterDetails.getMobileNumber())) {
				existingRecruiterDetails.setMobileNumber(recruiterDetails.getMobileNumber());
			}
			if(Objects.nonNull(recruiterDetails.getEmail())) {
				existingRecruiterDetails.setEmail(recruiterDetails.getEmail());
			}
			if(Objects.nonNull(recruiterDetails.getAddress())) {
				existingRecruiterDetails.setAddress(recruiterDetails.getAddress());
			}
			
			
			
			
			
			
			
			
			recruiterRepo.save(existingRecruiterDetails);
			log.info(existingRecruiterDetails.toString());
			
		}
		
		
		
		// User does not exist so continuing with creating a new user
		
		else {
		
		
		//String encryptedPassword = passwordEncoder.encode(jobSeekerDetailsRequest.getPassword());
//		JobSeekerDetails details =  JobSeekerDetails.builder()
//				.firstName(jobSeekerDetailsRequest.getFirstName())
//				.lastName(jobSeekerDetailsRequest.getLastName())
//				.mobileNumber(jobSeekerDetailsRequest.getMobileNumber())
//				.email(jobSeekerDetailsRequest.getEmail())
//				.password(encryptedPassword)
//				.qualification(jobSeekerDetailsRequest.getQualification())
//				.skillSet(jobSeekerDetailsRequest.getSkillSet())
//				.experience(jobSeekerDetailsRequest.getExperience())
//				.summary(jobSeekerDetailsRequest.getSummary())
//				.address(jobSeekerDetailsRequest.getAddress())
//				.build();
//		repo.save(details);
		//jobSeekerDetailsRequest.setPassword(encryptedPassword);
		recruiterRepo.save(recruiterDetails);
		
		log.info(recruiterDetails.toString());
		//repo.save(null)
	}
		
	}*/
public ResponseEntity<UserInfoResponse> signInDetails(SignInDetailsRequest signInDetailsRequest) {
		//String message="";
		RecruiterDetails recruiterSigninDetails = recruiterRepo.findByUsername(signInDetailsRequest.getUsername());
		if (recruiterSigninDetails!= null) 
		{
		 String password = signInDetailsRequest.getPassword();
		 String encodedPassword = recruiterSigninDetails.getPassword();
         Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
        if (isPwdRight) 
        {
        	String jwtToken = jwtUtils.generateTokenFromUsername(signInDetailsRequest.getUsername());
        	return ResponseEntity.ok()//.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        			//.header(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "false")
    		        .body(new UserInfoResponse(recruiterSigninDetails.getRecruiterId(),
    		        							recruiterSigninDetails.getUsername(),
    		        							recruiterSigninDetails.getEmail(),
    		        							jwtToken,
    		        							Arrays.asList("ROLE_RECRUITER")
    		                                   ));
		    

		} else 
		{
			throw new CustomException("Invalid Credentials", "", 500);
	     }

		}
		else {
			throw new CustomException("Username not found.Please Register!", "", 500);
        }
		
	}
	/*public String signInDetails(SignInDetailsRequest signInDetailsRequest) {
		
		Authentication authentication = authenticationManager
		        .authenticate(new UsernamePasswordAuthenticationToken(signInDetailsRequest.getUserName(), signInDetailsRequest.getPassword()));

		    SecurityContextHolder.getContext().setAuthentication(authentication);

		    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
		    
		    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
		        .body(new UserInfoResponse(userDetails.getId(),
		                                   userDetails.getUsername(),
		                                   userDetails.getEmail()
		                                   ));
	}*/


public List<RecruiterDetails> allDetails() {
		return recruiterRepo.findAll();
	}
	


public RecruiterDetails getRecruiterByUserName(String userName) {
	// TODO Auto-generated method stub
	log.info("user name"+userName);
	
	 RecruiterDetails recruiterDetails =recruiterRepo.findByUsername(userName);
	 if(Objects.isNull(recruiterDetails)) {
		 
		 throw new CustomException("Recruiter not found with name :"+userName, "NOT_FOUND", 404);
	 }
	 return recruiterDetails;
}



public RecruiterDetails getRecruiterById(long id) {
	// TODO Auto-generated method stub
	
	RecruiterDetails recruiterDetails= recruiterRepo.findById(id).orElseThrow(()->new CustomException("Recruiter not found with Id: "+id,"NOT_FOUND",404));
	return recruiterDetails;
}



public void deleteUserByUserName(String userName) {
	// TODO Auto-generated method stub
	 RecruiterDetails recruiterDetails =recruiterRepo.findByUsername(userName);
	 if(Objects.isNull(recruiterDetails)) {
		 
		 throw new CustomException("Recruiter not found with name :"+userName, "NOT_FOUND", 404);
	 }
	recruiterRepo.deleteByUsername(userName);
	
}

}
