package com.oracle.oBootDbConnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootDbConnect.domain.Member7;
import com.oracle.oBootDbConnect.service.MemberService;

@Controller
public class MemberController {
	//  생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어줌
    //  객체 의존관계를 외부에서 넣어주는 것을 DI (Dependency Injection), 의존성 주입이라 함
    //  이전 에서는 개발자가 직접 주입했고, 여기서는 @Autowired에 의해 스프링이 주입
    //  @Component 를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록된다.
	// 아래 3개는 직접 설정을 해주어야, 설정이 들어가진다.
    //    @Controller
    //    @Service
    //    @Repository
	
	
	//  스프링 빈을 등록하는 2가지 방법
  //    컴포넌트 스캔(@)과 자동 의존관계(DI) 설정
  //    자바 코드로 직접 스프링 빈 등록하기	
	
	
	//instance 3번 로직이다. (MemberService가 instance화 되어서 들어와지는 것@)
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	//Mapping에서 value 안의 members는 각 파트별로 나눠두기 위해서
	//지금은 URL의 일종이다.
	@GetMapping(value="/members/memberForm")
	public String createMemberForm() {
		System.out.println("MemberController /members/memberForm start...");
		return "members/createMemberForm";
	}

	@PostMapping(value="/members/newSave")
	public String memberSave(Member7 member) {
		System.out.println("MemberController memberSave start...");
		memberService.memberSave(member);
		//redirect는 controller에 /를 찾는다 -> 따라서 Home controller로!!
		return "redirect:/";
	}
	
	@GetMapping(value="/members/memberList")
	public String memberLists(Model model) {
		List<Member7> memberList = memberService.findMembers();
		model.addAttribute("members", memberList);
		return "members/memberList";
	}
}
