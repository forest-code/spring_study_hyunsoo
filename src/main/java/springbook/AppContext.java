package springbook;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.jdbc.Driver;

import springbook.user.service.DummyMailSender;
import springbook.user.service.UserServiceTest.TestUserService;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "springbook.user")
@Import(SqlServiceContext.class)
public class AppContext {
	
	/**
	 * DB 연결과 트랜잭션
	 */
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/testdb?characterEncoding=UTF-8");
		dataSource.setUsername("spring");
		dataSource.setPassword("book");
		
		return dataSource;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}
	
	
	/**
	 * --------------------------------------------------------------------------------------------
	 * Production 용 설정정보
	 */
	
	@Configuration
	@Profile("production")
	public static class ProductionAppContext {
		
		@Bean
		public MailSender mailSender() {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost("mail.mycompany.com");
			return mailSender;
		}
		
	}
	
	
	/**
	 * --------------------------------------------------------------------------------------------
	 * Test 용 설정정보
	 */
	
	@Configuration
	@Profile("test")
	public static class TestAppContext {
		
		@Bean
		public TestUserService testUserService() {
			return new TestUserService();
		}

		@Bean
		public MailSender mailSender() {
			return new DummyMailSender();
		}
		
	}
}
