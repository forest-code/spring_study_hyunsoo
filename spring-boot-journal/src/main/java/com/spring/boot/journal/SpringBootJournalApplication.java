package com.spring.boot.journal;

import java.io.PrintStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.spring.boot.journal.domain.Journal;
import com.spring.boot.journal.repository.JournalRepository;

import lombok.Getter;
import lombok.Setter;

@SpringBootApplication(exclude={ActiveMQAutoConfiguration.class})
public class SpringBootJournalApplication implements CommandLineRunner, ApplicationRunner{
	
	private Logger log = LoggerFactory.getLogger(SpringBootJournalApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBootJournalApplication.class);

		// 더 많은 기능은 여기에 추가가 가능합니다.
		app.setBanner(new Banner() {

			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
				out.print("\n\n\t나만의 멋진 배너랍니다!\n\n".toUpperCase());
			}
			
		});
		
		Logger log = LoggerFactory.getLogger(SpringBootJournalApplication.class);
		app.addListeners(new ApplicationListener<ApplicationEvent>() {

			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				log.info("#### > " + event.getClass().getCanonicalName());
				
				if (event.getClass().equals(ApplicationReadyEvent.class)) {
					log.info("***** 스프링 부트 어플리케이션이 정상적으로 실행됬습니다. *****");
				}
			}
			
		});
		
		app.run(args);
	}
	
	@Bean
	InitializingBean saveData(JournalRepository repo) {
		return () -> {
			repo.save(Journal.builder()
					.title("스프링 부트 입문")
					.summary("오늘부터 스프링 부트 공부하기 시작했다.")
					.date("07/19/2017")
					.build());
			repo.save(Journal.builder()
					.title("간단한 스프링 부트 프로젝트")
					.summary("스프링 부트 프로젝트를 처음 만들어보았다.")
					.date("07/31/2017")
					.build());
			repo.save(Journal.builder()
					.title("스프링 부트 해부")
					.summary("스프링 부트를 자세히 살펴보았다.")
					.date("01/08/2017")
					.build());
			repo.save(Journal.builder()
					.title("스프링 부트 클라우드")
					.summary("클라우드 파운드리를 응용한 스프링 부트를 공부하여 마이크로서비스 아키텍쳐를 적용해보고 싶다.")
					.date("02/08/2017")
					.build());
		};
	}
	
	@Bean
	String info() {
		return "그냥 간단한 문자열 빈 입니다.";
	}
	
	@Autowired
	String info;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("## > ApplicationRunner 구현체...");
		log.info("info 빈에 액세스: " + info);
		args.getNonOptionArgs().forEach(file -> log.info(file));
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("## > CommandLineRunner 구현체...");
		log.info("info 빈에 액세스: " + info);
		for (String arg : args) {
			log.info(arg);
		}
	}
	
	@Bean
	CommandLineRunner myMethod() {
		return args -> {
			log.info("## > CommandLineRunner 구현체... 람다 사용");
			log.info("info 빈에 액세스: " + info);
			for (String arg : args) {
				log.info(arg);
			}
		};
	}
	
	@Value("${server.ip}")
	private String serverIp;
	
	@Bean
	CommandLineRunner values() {
		return args -> {
			log.info(" > 서버 IP: " + serverIp);
		};
	}
	
	/**
	 * ------------------------------------------------------------------------
	 * Pro Spring Boot Book 3.4
	 */
	@Value("${myapp.server.ip}")
	private String myServerIp;
	
	@Autowired
	MyAppProperties props;
	
	@Component
	@ConfigurationProperties(prefix = "myapp")
	@Getter
	@Setter
	public static class MyAppProperties {
		private String name;
		private String description;
		private String serverIp;
	}
	
	@Bean
	CommandLineRunner myappValues() {
		return args -> {
			log.info(" > 서버 IP: " + myServerIp);
			log.info(" > 애플리케이션명: " + props.getName());
			log.info(" > 애플리케이션 정보: " + props.getDescription());
		};
	}
	
}

@Component
class MyComponent {
	
	private static final Logger log = LoggerFactory.getLogger(MyComponent.class);
	
	@Autowired
	public MyComponent(ApplicationArguments args) {
		boolean enable = args.containsOption("enable");
		if (enable) {
			log.info("## > enable 옵션을 주셨네요!");
		}
		
		List<String> _args = args.getNonOptionArgs();
		log.info("## > 다른 인자...");
		if (!_args.isEmpty()) {
			_args.forEach(file -> log.info(file));
		}
	}
}
