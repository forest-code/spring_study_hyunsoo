package com.spring.boot.journal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by hyunsoo0813 on 2017. 8. 16..
 */
@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class ResourceOAuthSecurityConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").authenticated();
    }

    /**
     * security.oauth2.client.clientId = c37d9ff6-4bba-48f7-9995-172ef97dac8e
     * security.oauth2.client.secret = 5b3c7e99-6806-4104-9a7f-ff8de228be38
     *
     * {
     *      "access_token":"5b409938-d13a-4086-a232-90a0151eec7c",
     *      "token_type":"bearer",
     *      "refresh_token":"859d2069-137b-4eb9-8d5d-20a563ba08ef",
     *      "expires_in":43199,
     *      "scope":"read"}
     */
}
