package com.paimon.QLBanVePaimon.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigs {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                
                .authorizeHttpRequests(
                        (request) -> request.requestMatchers("/authen", "/api/v1/swagger-ui/index.html").permitAll()
                                .anyRequest().authenticated());

        return httpSecurity.build();

    }
}
