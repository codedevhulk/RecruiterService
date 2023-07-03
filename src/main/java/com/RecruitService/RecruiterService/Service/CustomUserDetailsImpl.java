/*package com.RecruitService.RecruiterService.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.RecruitService.RecruiterService.Entity.RecruiterDetails;
import com.RecruitService.RecruiterService.Repository.RecruiterRepo;

@Service
public class CustomUserDetailsImpl implements UserDetailsService {
	// why am I writing this class
    @Autowired
    RecruiterRepo recruiterRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    	RecruiterDetails recruiter = recruiterRepo.findByUsername(username);
    	//recruiter.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
    	//System.out.println(recruiter.get().getPassword());
    	//RecruiterDetails obj=recruiter.get();
        UserDetails user = User.withUsername(recruiter.getUsername()).password(recruiter.getPassword()).authorities("USER").build();
        return user;
        
    }

}*/