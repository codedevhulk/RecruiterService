package com.RecruitService.RecruiterService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RecruitService.RecruiterService.JwtUtils;
import com.RecruitService.RecruiterService.Entity.RecruiterDetails;
import com.RecruitService.RecruiterService.Model.RecruiterSignupResponse;
import com.RecruitService.RecruiterService.Model.SignInDetailsRequest;
import com.RecruitService.RecruiterService.Model.UserInfoResponse;
import com.RecruitService.RecruiterService.Service.RecruiterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/recruiter")
@Slf4j
//@CrossOrigin(origins="http://localhost:3000",allowCredentials="true")
public class RecruiterController {

	@Autowired
	RecruiterService recruiterService;
	@Autowired
	JwtUtils jwtUtils;

	// Rajesh's code

	////////////////////////////////////////////
	// To check whether the Recruiter exists or not, if exists updates his details,
	//////////////////////////////////////////// If not create a new recruiter

	@PutMapping("/updateprofile")
	public boolean signupDetails(@RequestBody RecruiterDetails recruiterDetails) {

		log.info(recruiterDetails.toString());
		log.info(recruiterDetails.getAddress());
		// recruiterService.signupDetails(recruiterDetails);
		recruiterService.updateRecruiterDetails(recruiterDetails);
//		log.info("Passing the jobSeekerDetails to service layer"+ jobSeekerDetailsRequest.toString());
		return true;
	}

	// Login functionality of recruiter

	@GetMapping("/all")
	public List<RecruiterDetails> allDetails() {
		return recruiterService.allDetails();
	}

	@GetMapping("/{userName}")
	public ResponseEntity<RecruiterDetails> getRecruiterByUserName(@PathVariable String userName) {

		RecruiterDetails recruiterDetails = recruiterService.getRecruiterByUserName(userName);
		return new ResponseEntity<>(recruiterDetails, HttpStatus.OK);

	}

	// To Get Recruiter details by his Id

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<RecruiterDetails> getRecruiterById(@PathVariable long id) {

		RecruiterDetails recruiterDetails = recruiterService.getRecruiterById(id);

		return new ResponseEntity<>(recruiterDetails, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{userName}")
	public String deleteUserByUserName(@PathVariable String userName) {

		recruiterService.deleteUserByUserName(userName);
		return "user has been deleted with name " + userName;
	}

	// Anil's
	// code------------------------------------------------------------------------

	@PostMapping("/signup")
	public ResponseEntity<RecruiterSignupResponse> createNewRecruiter(@RequestBody RecruiterDetails rd) {

		
		RecruiterSignupResponse response=recruiterService.createNewRecruiter(rd);
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);// HttpStatus.ok
	}

	@PostMapping("/signin")
	public ResponseEntity<UserInfoResponse> signInDetails(@RequestBody SignInDetailsRequest signInDetailsRequest) {
		ResponseEntity<UserInfoResponse> response = recruiterService.signInDetails(signInDetailsRequest);
		return response;
	}

	@GetMapping("/jwtvalidate")
	public boolean jwtValidate(@PathVariable String value) {
		return jwtUtils.validateJwtToken(value);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<RecruiterDetails> getRecruiterDetails(@PathVariable int id) {

		RecruiterDetails rd = recruiterService.getRecruiterDetails(id);
		return new ResponseEntity<>(rd, HttpStatus.OK);// HttpStatus.ok
	}

	@PutMapping("/update")
	public ResponseEntity<RecruiterDetails> updateRecruiterDetails(@RequestBody RecruiterDetails rd) {

		RecruiterDetails obj = recruiterService.updateRecruiterDetails(rd);
		return new ResponseEntity<>(obj, HttpStatus.OK);// HttpStatus.ok
	}

	/*
	 * @DeleteMapping("/delete") public ResponseEntity<RecruiterDetails>
	 * deleteRecruiterDetails(@PathVariable int id) {
	 * 
	 * RecruiterDetails obj=serviceobj.deleteRecruiterDetails(rd); return new
	 * ResponseEntity<>(obj,HttpStatus.OK);//HttpStatus.ok }
	 */

}
