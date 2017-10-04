package com.selsoft.trackme.email.test;
import java.util.ArrayList;
import java.util.List;

import com.selsoft.trackme.email.config.EmailServiceBeanConfiguration;
import com.selsoft.trackme.email.service.MailSenderService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MailServiceTest {
	@SuppressWarnings("resource")
		public static void main(String[] args) {
			ApplicationContext context = new AnnotationConfigApplicationContext(
					EmailServiceBeanConfiguration.class);
			
			System.setProperty("java.net.preferIPv4Stack", "true");
			MailSenderService service = (MailSenderService) context
					.getBean("mailSenderService");
			List<String> mailId = new ArrayList<>();
			mailId.add("dibya.pro@gmail.com");
			System.out.println(service.sendMail(mailId, "Sample Text",
					"Hi Basant How are you"));
		}
	

}
