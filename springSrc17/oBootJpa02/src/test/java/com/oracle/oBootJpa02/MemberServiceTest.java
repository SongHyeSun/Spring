package com.oracle.oBootJpa02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.repository.MemberRepository;
import com.oracle.oBootJpa02.service.MemberService;

//@SpringBootTest : 스프링 부트 띄우고 테스트(이게 없으면 @Autowired 다 실패)
//반복 가능한 테스트 지원, 각각의 테스트를 실행할 때마다 트랜잭션을 시작하고
//테스트가 끝나면 트랜잭션을 강제로 롤백

@SpringBootTest
@Transactional
public class MemberServiceTest {
	//service와 dao까지 injection 시켜놓기!
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@BeforeEach
	public void before1() {
		System.out.println("Test @BeforeEach...");
	}
	
	//저장 method
	//Test는 반드시 @Test가 있어야 한다.
	@Test
//	@Rollback(value = false)
	public void memberSave() {
		//1. 조건
		Member member = new Member();
		member.setTeamname("고구려");
		member.setName("강이식");
		//2. 행위(Action)
		Member member3 = memberService.memberSave(member);
		//3. 결과(Result)
		System.out.println("MemberServiceTest memberSave member3-> "+member3);
		
	}
	
	
	@Test
	//조회 method
	public void memberFind() {
		//1. 조건
		//회원조회 --> 이순신
		Long findId = 4L;
		//2. 행위(Action)
		Member member = memberService.findByMember(findId);
		//3. 결과
		System.out.println("MemberServiceTest memberFind member->"+member);
	}
}
