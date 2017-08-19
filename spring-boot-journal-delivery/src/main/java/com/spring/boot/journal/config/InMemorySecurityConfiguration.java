package com.spring.boot.journal.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

@Configuration
@EnableGlobalAuthentication
public class InMemorySecurityConfiguration {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("spring").password("P@boot").roles("USER")
				.and()
				.withUser("admin").password("P@ssw0rd").roles("USER", "ADMIN");
	}
}
