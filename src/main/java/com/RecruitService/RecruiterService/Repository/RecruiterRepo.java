package com.RecruitService.RecruiterService.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.RecruitService.RecruiterService.Entity.RecruiterDetails;

@Repository
public interface RecruiterRepo extends JpaRepository<RecruiterDetails,Long>{
	
	
	@Transactional
	RecruiterDetails findByUserName(String userNameName);
	@Transactional
	RecruiterDetails findByEmail(String email);
    
	@Transactional
	void deleteByUserName(String userName);

    @Transactional
    RecruiterDetails findByRecruiterId(long id);
}
