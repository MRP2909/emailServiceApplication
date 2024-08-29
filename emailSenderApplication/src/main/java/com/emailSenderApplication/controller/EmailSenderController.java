package com.emailSenderApplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emailSenderApplication.emailService.EmailService;
import com.emailSenderApplication.entity.EmailRequest;
import com.emailSenderApplication.helper.CustomResponse;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("api/emailSender")
public class EmailSenderController {

	private EmailService emailService;

	public EmailSenderController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping("/sendMail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {

		emailService.sendEmail(request.getTo(), request.getSubject(), request.getMessage());

		return ResponseEntity.ok(CustomResponse.builder().message("Email send sucfcessfully...!")
				.httpStatus(HttpStatus.OK).success(true).build());
	}

//	@PostMapping("/sendEmail_With_File")
//	public ResponseEntity<CustomResponse> sendWithFile(@RequestBody EmailRequest request) throws MessagingException {
//		emailService.sendEmailWithLocalFile(request.getTo(), request.getSubject(), request.getMessage());
//
//		return ResponseEntity.ok(CustomResponse.builder().message("Email send sucfcessfully...!")
//				.httpStatus(HttpStatus.OK).success(true).build());
//	}
}
