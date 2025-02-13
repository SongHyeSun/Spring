package com.oracle.oBootHello.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootHello.dto.Member1;
import com.oracle.oBootHello.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	//1번 방식. 전통적
//	MemberService memberService = new MemberService();
	//2. DI --> 생성자를 이용한 DI 방식 (constructor injection)
	private final MemberService memberService;
	//instance가 들어오는 것!
	
	//생성자는 Autowired 안해도 되는데, FM대로 해주기 위해!!
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value ="members/memberForm")
	public String memberForm() {
		System.out.println("MemberController/members/memberForm Start...");
		//view 단에서! resources templates 밑에 폴더 members 만들고 memberForm html을 만들어준다.
		return "members/memberForm";
		//(prefix : templates) + (members/memberForm) + (suffix -> .html)
	}
	
	@PostMapping(value ="/members/save")
	public String save(Member1 member1) {
		System.out.println("MemberController /members/save Start...");
		System.out.println("MemberController /members/save member1.getName()->"+member1.getName());
		Long id = memberService.memberSave(member1);
		System.out.println("MemberController /members/save id ->"+id);
		//redirect는 같은 controller 내에 것을 호출할 때!! /가 없을 static이 있는 index
		return "redirect:/";
	}
	
	@GetMapping(value ="/members/memberList")
	public String memberList(Model model) {
		logger.info("memberList start...");
		List<Member1> memberLists = memberService.allMembers();
		model.addAttribute("memberLists",memberLists);
		//sysout.printf 처럼 쓰일 수 있다.
		logger.info("memberLists.size()->{}",memberLists.size());
		
		//return에 view resolver!!
		// prefix 인 templates 밑에 members 밑에 memberList 그리고 suffix인 .html까지!
		// 해당 경로 밑에 memberList.html을 해주면 된다.
		return "members/memberList";
	}
}