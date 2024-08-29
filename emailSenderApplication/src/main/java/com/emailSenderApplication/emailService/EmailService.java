package com.emailSenderApplication.emailService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;

public interface EmailService {

	// send mail to single person
	void sendEmail(String to, String subject, String message);

	// send mail to multiple person
	void sendEmail(String[] to, String subject, String message);

	// send mail with html
	void sendEmailWithhtml(String to, String subject, String htmlContent);

	// send mail with file
	void sendEmailWithFile(String to, String subject, String message, File file);

	// send mail with InputStreamfile
	void sendEmailWithFile(String to, String subject, String message, InputStream file);
	
	// with file chat gpt code
	void sendEmailWithLocalFile(String to, String subject, String message,String pathToAttachment) throws MessagingException;
	
	//with file need to test with postman 
	 void sendEmailWithLocalFile(String to, String subject, String text, MultipartFile file) throws MessagingException, IOException;
	
}
