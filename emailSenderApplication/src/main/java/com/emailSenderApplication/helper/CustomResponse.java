package com.emailSenderApplication.helper;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomResponse {

	private String message;
	
	private HttpStatus httpStatus;
	
	private boolean success = false;
}
