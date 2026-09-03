package com.jsp.clinkNBuy.config;

import java.net.Authenticator.RequestorType;
import java.security.AuthProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jsp.clinkNBuy.security.JwtFilter;

import jakarta.mail.Session;
import jakarta.servlet.Filter;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
	
	JwtFilter jwtFilter;
	UserDetailsService userDetailsService;
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	SecurityFilterChain chain(HttpSecurity http) throws Exception {
//		return http.csrf(x -> x.disable())
//				.authorizeHttpRequests(req -> req.requestMatchers("/api/v1/user/auth/**").permitAll()).build();
//	}
	
	@Bean
	SecurityFilterChain chain(HttpSecurity http) throws Exception {
	    return http
	            .csrf(csrf -> csrf.disable())
	            .sessionManagement(Session ->Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	            .authorizeHttpRequests(req ->
	            req.requestMatchers("/api/v1/users/auth/**").permitAll())
	            .authenticationProvider(authenticationProvider())
	            .addFilterBefore((Filter) jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
		
	}

private AuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	authenticationProvider.setUserDetailsService(userDetailsService);
	authenticationProvider.setPasswordEncoder(encoder());
	
	// TODO Auto-generated method stub
	return authenticationProvider;
}


	

}
