package com.emailSenderApplication.emailServiceImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emailSenderApplication.emailService.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	private org.slf4j.Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	private JavaMailSender mailSender;

	@Autowired
	public EmailServiceImpl(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("milindpagar16@gmail.com");

		mailSender.send(simpleMailMessage);
		logger.info("Email has been sent successfully...");

	}

	@Override
	public void sendEmail(String[] to, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("milindpagar16@gmail.com");

		mailSender.send(simpleMailMessage);
		logger.info("Email has been sent successfully...");

	}

	@Override
	public void sendEmailWithhtml(String to, String subject, String htmlContent) {

		MimeMessage simpleMailMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(simpleMailMessage, true, "UTF-8");

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(htmlContent, true);
			helper.setFrom("milindpagar16@gmail.com");
			mailSender.send(simpleMailMessage);

			logger.info("html mail send successfully");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	// TODO : Not worked properly check once again
	@Override
	public void sendEmailWithFile(String to, String subject, String message, File file) {

		MimeMessage mimeMessage = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(message);
			helper.setFrom("milindpagar16@gmail.com");

			FileSystemResource fileSystemResource = new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(), file);
			mailSender.send(mimeMessage);
			logger.info("html mail send successfully");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void sendEmailWithFile(String to, String subject, String message, InputStream is) {

//		MimeMessage SimpleMailMessage = mailSender.createMimeMessage();
//
//		try {
//			MimeMessageHelper helper = new MimeMessageHelper(SimpleMailMessage, true);
//
//			helper.setTo(to);
//			helper.setSubject(subject);
//			helper.setText(message);
//			helper.setFrom("milindpagar16@gmail.com");
//			File file = new File("test.png");
//
//			Files.copy(is, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
//			FileSystemResource fileSystemResource = new FileSystemResource(file);
//			helper.addAttachment(fileSystemResource.getFilename(), file);
//			mailSender.send(SimpleMailMessage);
//
//			mailSender.send(SimpleMailMessage);
//			logger.info("html mail send successfully");
//
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		} catch (IOException e) {
//
//			throw new RuntimeException();
//		}

		
		 MimeMessage mimeMessage = mailSender.createMimeMessage();

	        try {
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	            helper.setTo(to);
	            helper.setSubject(subject);
	            helper.setText(message);
	            helper.setFrom("milindpagar16@gmail.com");

	            // Create a temporary file
	            File tempFile = File.createTempFile("attachment-", ".tmp");
	            Files.copy(is, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

	            // Attach the file
	            helper.addAttachment(tempFile.getName(), tempFile);

	            // Send the email
	            mailSender.send(mimeMessage);

	            // Delete the temporary file after sending
	            tempFile.delete();

	            logger.info("Email with attachment sent successfully!");

	        } catch (MessagingException | IOException e) {
	            throw new RuntimeException("Failed to send email with attachment", e);
	        }
	}
//TODO : This works normally in junit testing 
	@Override
	public void sendEmailWithLocalFile(String to, String subject, String message, String pathToAttachment) throws MessagingException {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(message);

		// Attach the file
		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment(file.getFilename(), file);

		mailSender.send(mimeMessage);
	}

	
	//TODO : this is for postman that we have to send file so and test in postman 
@Override
public void sendEmailWithLocalFile(String to, String subject, String message, MultipartFile file) throws MessagingException,IOException {

	MimeMessage mimeMessage = mailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	
//	helper.setTo(to);
//	helper.setSubject(subject);
//	helper.setText(message);
//
//	// Attach the file
//	FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
//	helper.addAttachment(file.getFilename(), file);
//
//	mailSender.send(mimeMessage);
//	
//	 MimeMessage message = emailSender.createMimeMessage();
//     MimeMessageHelper helper = new MimeMessageHelper(message, true);

     helper.setTo(to);
     helper.setSubject(subject);
     helper.setText(message);

     // Attach the file
     helper.addAttachment(file.getOriginalFilename(), file);

     mailSender.send(mimeMessage);
}

}
