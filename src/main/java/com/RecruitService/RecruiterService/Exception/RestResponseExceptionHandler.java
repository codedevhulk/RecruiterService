package com.RecruitService.RecruiterService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.RecruitService.RecruiterService.Exception.response.ErrorMessage;
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(CustomException.class)
	@ResponseBody
	@ResponseStatus
	public ErrorMessage customExceptionHandler(CustomException e){
		
		ErrorMessage errorMessage=new ErrorMessage(e.getMessage(),e.getErrorCode());
		
		
		return errorMessage;
	}
	
	
}
