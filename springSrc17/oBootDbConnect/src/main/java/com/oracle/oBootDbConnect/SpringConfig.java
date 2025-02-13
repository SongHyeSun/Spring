package com.oracle.oBootDbConnect;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootDbConnect.repository.JdbcMemberRepository;
import com.oracle.oBootDbConnect.repository.MemberRepository;
import com.oracle.oBootDbConnect.repository.MemoryMemberRepository;

@Configuration
public class SpringConfig {
	private DataSource dataSource;
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public MemberRepository memberRepository() {
		//DataSource를 집어넣야 datasource를 가지고 JdbcMemberRepository를 만들 수 있다.
//		return new JdbcMemberRepository(dataSource);  --> oracle로 부품교체
		return new MemoryMemberRepository();		//--> Memory로 부품 교체
		//MemoryMemberRepository를 가보면 생성자가 없다 -> 기본생성자 생긴다.
		//JdbcMemberRepository는 생성자가 존재한다. 따라서 기본생성자가 생성되지 않기 때문에,
		// 반드시 dataSource를 정확히 넣어주어야 한다.
	}
}
