package springbook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

import springbook.user.service.DummyMailSender;
import springbook.user.service.UserServiceTest.TestUserService;

@Configuration
public class TestAppContext {

	@Bean
	public TestUserService testUserService() {
		return new TestUserService();
	}

	@Bean
	public MailSender mailSender() {
		return new DummyMailSender();
	}

}
