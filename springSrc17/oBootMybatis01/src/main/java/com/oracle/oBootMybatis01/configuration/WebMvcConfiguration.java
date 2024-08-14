package com.oracle.oBootMybatis01.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.oracle.oBootMybatis01.service.SampleInterceptor;

@Configuration

public class WebMvcConfiguration implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//누군가 URL / interCeptor --> SampleInterceptor() 처리해줌
		//service에 있는 SampleInterceptor
		registry.addInterceptor(new SampleInterceptor()).addPathPatterns("/interCeptor");
		//							/interCeptor가 나오면 	SampleInterceptor가 가로채기				
	}

}
