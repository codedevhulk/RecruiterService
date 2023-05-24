package com.RecruitService.RecruiterService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SignInDetailsRequest {



	String email;
	String password;
}
