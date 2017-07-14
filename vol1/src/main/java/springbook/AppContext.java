package springbook;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.jdbc.Driver;

import springbook.user.dao.UserDao;
import springbook.user.service.DummyMailSender;
import springbook.user.sqlservice.config.EnableSqlService;
import springbook.user.sqlservice.config.SqlMapConfig;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "springbook.user")
//@Import(SqlServiceContext.class)
@EnableSqlService
@PropertySource("/database.properties")
public class AppContext implements SqlMapConfig {

	/**
	 * DB 연결과 트랜잭션
	 */

	@Value("${db.driverClass}")
	private Class<? extends Driver> driverClass;

	@Value("${db.url}")
	private String url;

	@Value("${db.username}")
	private String username;

	@Value("${db.password}")
	private String password;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Override
	public Resource getSqlMapResource() {
		return new ClassPathResource("sqlmap.xml", UserDao.class);
	}

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

		dataSource.setDriverClass(this.driverClass);
		dataSource.setUrl(this.url);
		dataSource.setUsername(this.username);
		dataSource.setPassword(this.password);

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

		/*@Bean
		public TestUserService testUserService() {
			return new TestUserService();
		}*/

		@Bean
		public MailSender mailSender() {
			return new DummyMailSender();
		}

	}

}
