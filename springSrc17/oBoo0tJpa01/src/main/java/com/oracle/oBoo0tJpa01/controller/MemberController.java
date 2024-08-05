package com.oracle.oBoo0tJpa01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBoo0tJpa01.domain.Member;
import com.oracle.oBoo0tJpa01.service.MemberService;

@Controller
@Transactional
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private final MemberService memberService;
	
	@Autowired
	public MemberController (MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value = "/members/new")
	public String createForm() {
		System.out.println("MemberController /members/new start... ");
		return "members/createMemberForm";
	}
	
	@PostMapping(value = "members/save")
	// 							DTO로 불러준다!!!
	public String memberSave(Member member) {
		System.out.println("MemberController memberSave start...");
		System.out.println("MemberController member->"+member);
		System.out.println("member.getId()->"+member.getId());
		System.out.println("member.getName()->"+member.getName());
		
		//transaction이 유지가 된 상태에서!!
		memberService.memberSave(member);
		System.out.println("MemberController memberSave After...");
		
		return "redirect:/";
	}
	
}
