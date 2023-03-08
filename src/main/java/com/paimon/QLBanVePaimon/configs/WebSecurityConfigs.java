package com.paimon.QLBanVePaimon.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigs {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        
        httpSecurity.csrf().disable()

                .authorizeHttpRequests().requestMatchers("/authen/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                // .anyRequest()
                // .authenticated()
                .requestMatchers("/managers/**").hasRole("ADMIN")
                
                
                    
                        // .requestMatchers(HttpMethod.DELETE,"/rest/**").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.GET,"/rest/**").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.POST,"/rest/**").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.PUT,"/rest/**").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.TRACE,"/rest/**").hasRole("ADMIN")
                        .and()


                        .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }


}
