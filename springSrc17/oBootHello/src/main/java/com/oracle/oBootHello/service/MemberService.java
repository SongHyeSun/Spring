package com.oracle.oBootHello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootHello.dto.Member1;
import com.oracle.oBootHello.repository.MemberRepository;
import com.oracle.oBootHello.repository.MemoryMemberRepository;

@Service
public class MemberService {
	//실제 구현할 객체를 넣어주는 것이 일반적인
	//1. 전통적 (setter 방식) -> DI중에서
//	MemberRepository memberRepository = new MemoryMemberRepository();
	
	//2. DI--> 생성자 (Spring은 DI  생성자로 사용한다 주로 현장에서)
	//final은 상수라는 의미처럼 , 생성자를 바꾸지 않겠다는 의미!!!!
	private final MemberRepository memberRepository;	//선언
	
	//@Autowired를 생략해도 연결 되지만, FM으로 해주기 위해서!!!
	//instance로 연동시키겠다는 뜻!!
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public Long memberSave(Member1 member1) {
		System.out.println("MemberService memberSave start...");
		memberRepository.save(member1);
		return member1.getId();
	}
	
	public List<Member1> allMembers() {
		System.out.println("MemberService allMembers start...");
		List<Member1> memList = null;
		memList = memberRepository.findAll();
		System.out.println("MemberService memList.size()->"+memList.size());
		return memList;
	}
}
