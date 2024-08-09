package com.oracle.oBootMybatis01.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	//이 암호와 logic 만 쓰기 위해서
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	//얘를 써주는데, 대신 권한을 모두에게 준다.
	//update logic 시작 때 !!! 주석처리해준다.
//	@Bean
//	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests()
//			.anyRequest()
//			.permitAll();
//		return http.build();
//	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.cors(cors-> cors.disable())
		.csrf(csrf-> csrf.disable());
		
		return http.build();
	}
}
