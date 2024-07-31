package com.oracle.oBootDbConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootDbConnect.domain.Member7;
import com.oracle.oBootDbConnect.repository.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	
	@Autowired
	// 생성자에 instance화 되어있는 상황이 되기 위해서!! -> 따라서 @Repository를 꼭 해주어야 한다.
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	//회원가입
	public Long memberSave(Member7 member7) {
		System.out.println("MemberService memberSave start...");
		memberRepository.save(member7);
		return member7.getId();
	}
	
	//전체 회원 조회
	public List<Member7> findMembers() {
		System.out.println("MemberService allMembers start...");
		List<Member7> memList = null;
		memList = memberRepository.findAll();
		System.out.println("MemberService memList.size()->"+memList.size());
		return memList;
		
//		선생님 답안
//		System.out.println("MemberService allMembers start...");
//		return memberRepository.findAll();
	}

}
