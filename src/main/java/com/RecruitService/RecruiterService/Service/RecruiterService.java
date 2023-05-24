package com.RecruitService.RecruiterService.Service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.RecruitService.RecruiterService.Entity.RecruiterDetails;
import com.RecruitService.RecruiterService.Model.SignInDetailsRequest;
import com.RecruitService.RecruiterService.Repository.RecruiterRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RecruiterService {
	
	@Autowired
	RecruiterRepo recruiterRepo;
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public String createNewRecruiter(RecruiterDetails rd) {
		
		recruiterRepo.save(rd);
		return "Successfully Inserted";
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
	
public void signupDetails(RecruiterDetails recruiterDetails) {
		
		
		
		
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
		
	}
public String signInDetails(SignInDetailsRequest signInDetailsRequest) {
		String message="";
		RecruiterDetails recruiterSigninDetails = recruiterRepo.findByEmail(signInDetailsRequest.getEmail());
		if (recruiterSigninDetails!= null) 
		{
		 String password = signInDetailsRequest.getPassword();
		 String encodedPassword = recruiterSigninDetails.getPassword();
         Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
        if (isPwdRight) 
        {
        	
        	
		    message="Login Success";
		    return message;

		} else 
		{
			message = "Login Failed";
			return message;
	     }

		}
		else {
            message="Email not exits";
            return message;
        }
		
	}




public List<RecruiterDetails> allDetails() {
		return recruiterRepo.findAll();
	}
	


public RecruiterDetails getRecruiterByUserName(String userName) {
	// TODO Auto-generated method stub
	log.info("user name"+userName);
	
	return recruiterRepo.findByUserName(userName);
}



public RecruiterDetails getRecruiterById(long id) {
	// TODO Auto-generated method stub
	
	RecruiterDetails recruiterDetails= recruiterRepo.findByRecruiterId(id);
	return recruiterDetails;
}



public void deleteUserByUserName(String userName) {
	// TODO Auto-generated method stub
	recruiterRepo.deleteByUserName(userName);
	
}

}
