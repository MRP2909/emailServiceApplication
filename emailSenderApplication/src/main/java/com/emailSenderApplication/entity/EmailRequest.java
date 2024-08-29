package com.emailSenderApplication.entity;

import jakarta.mail.Multipart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EmailRequest {

	private String to;

	private String subject;

	private String message;
	
	private String path;

	private Multipart file;
}
