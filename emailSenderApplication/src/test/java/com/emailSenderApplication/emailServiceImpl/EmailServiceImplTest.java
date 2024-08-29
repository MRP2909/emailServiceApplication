package com.emailSenderApplication.emailServiceImpl;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.emailSenderApplication.emailService.EmailService;

import jakarta.mail.MessagingException;

@SpringBootTest
class EmailServiceImplTest {

	@Autowired
	private EmailService emailServiceImpl;

	@Test
	void test() {
		System.out.println("junit test from mail sender application");
	}

	@Test
	void emailSenderTest() {

		System.out.println("Email sender application started....");

		emailServiceImpl.sendEmail("pallavipagar0212@gmail.com", "Email Application Test",
				"This message is from applcation testing Email service");

		System.out.println("Email tested successfully");
	}

	@Test
	void emailSendertMmultipleTest() {
		System.out.println("multiples senders start...");

		String[] to = { "milind.mech1997@gmail.com", "pallavipagar0212@gmail.com" };
		emailServiceImpl.sendEmail(to, "multiple Email Application Test",
				"This message is from applcation testing Email service");

		System.out.println("Multiple sender end ...");
	}

	@Test
	void emailSendertHTMLTest() {
		System.out.println("multiples senders start...");

		String html = ""+""
				+ "<h1 style='color:red; border:2px solid red;'>Welcome to mail content service application...</h1>\r\n"
				+ ""+"";
		emailServiceImpl.sendEmailWithhtml("milind.mech1997@gmail.com", "multiple Email Application Test",
				html);

		System.out.println("Multiple sender end ...");
	}
	
	@Test
	void emailSendertWithFileTest() {
		System.out.println("with file mail send start...");

		String html = ""+""
				+ "<h1 style='color:red; border:2px solid red;'>Welcome to mail content service application...</h1>\r\n"
				+ ""+"";
		emailServiceImpl.sendEmailWithFile("milind.mech1997@gmail.com", "multiple Email Application Test",
				html, new File("C:\\\\Users\\\\Milind Pagar\\\\Downloads\\\\1598435676835.jpg"));

		System.out.println("with file mail send end...");
	}
	
	

	@Test
	void emailSendertWithFileInputStreamTest() {
		System.out.println("with file mail send start...");

		String html = ""+""
				+ "<h1 style='color:red; border:2px solid red;'>Welcome to mail content service application...</h1>\r\n"
				+ ""+"";
		emailServiceImpl.sendEmailWithFile("milind.mech1997@gmail.com", "multiple Email Application Test",
				html, new File("C:\\Users\\Milind Pagar\\Downloads\\1598435676835.jpg"));

		System.out.println("with file mail send end...");
	}
	
	@Test
	void sendEmailWithLocalFile() throws MessagingException {
		
		
		System.out.println("with file mail send start...");

		String html = ""+""
				+ "<h1 style='color:red; border:2px solid red;'>Welcome to mail content service application...</h1>\r\n"
				+ ""+"";
		emailServiceImpl.sendEmailWithLocalFile("milind.mech1997@gmail.com", "multiple Email Application Test with file.",
				html, "C:\\Users\\Milind Pagar\\Desktop\\Important.txt");

		System.out.println("with file mail send end...");
		
	}
}
