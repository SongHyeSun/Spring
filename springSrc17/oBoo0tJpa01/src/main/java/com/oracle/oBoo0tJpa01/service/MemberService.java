package com.oracle.oBoo0tJpa01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBoo0tJpa01.domain.Member;
import com.oracle.oBoo0tJpa01.repository.MemberRepository;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	//회원가입
	public Long memberSave(Member member) {
		System.out.println("MemberService memberSave member->"+member);
		memberRepository.memberSave(member);
		System.out.println("MemberService memberSave After...");
		return member.getId();
	}
	
}
