package com.oracle.oBootHello.repository;

import java.util.List;

import com.oracle.oBootHello.dto.Member1;

public interface MemberRepository {
	//저장 할 프로그램 -> DB가 아닌 Memory에 저장하는 것!!
	Member1 		save(Member1 member1);
	//(Memory상에 저장한 것을) 조회 할 프로그램
	List<Member1>	findAll();
}
