package com.oracle.oBootBoard;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.oracle.oBootBoard.dao.BDao;
import com.oracle.oBootBoard.dao.JdbcDao;

@Configuration
public class SpringConfig {
	private DataSource dataSource;
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	//	   바로 JdbcDao로 선언하지 않고 BDao로 선언한 이유 : DB가 바뀌어도 사용할 수 있게
	//	   선언을 JdbcDao 로 하면 부품을 교체할 수 없기 때문에 (약결합을 쓰기 위해서)
	public BDao jdbcDao() {
		//JDBC
		return new JdbcDao(dataSource);
		//Memory 상 저장하는 repository
//		return new MemoryMemberRepository();
	}
}
