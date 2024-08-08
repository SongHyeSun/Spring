package com.oracle.oBootJpaApi01.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.naming.spi.DirStateFactory.Result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootJpaApi01.domain.Member;
import com.oracle.oBootJpaApi01.service.MemberService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Controller + @ResponseBody = @RestController
@RestController
//private static final Logger logger = LoggerFactory.getLogger(JpaRestApiController.class);
@Slf4j
@RequiredArgsConstructor
public class JpaRestApiController {
	private final MemberService memberService;
	
	@RequestMapping("/helloText")
	public String helloText() {
		System.out.println("JpaRestApiController Start...");
		String hello = "안녕";
		// 		StringConverter
		return hello;
	}
	
	//Bad Api
	@GetMapping("/restApi/v1/members")
	public List<Member> membersVer1() {
		System.out.println("JpaRestApiController /restApi//v1/members start...");
		//return return되어서 결국에는 listMember에 쌓일 것이다.
		List<Member> listMember = memberService.getListAllMember();
		System.out.println("JpaRestApiController /restApi//v1/members listMember.size() -> "+listMember.size());
		return listMember;
	}
	
	//Good Api Easy Version
	//목표 : 이름 & 급여 만 전송
	@GetMapping("/restApi/v21/members")
	public Result membersVer21() {
		List<Member> fineMembers = memberService.getListAllMember();
		System.out.println("JpaRestApiController restApi/v21/members findMembers.size()-> "+fineMembers.size());
		
		//보내주는 Data의 List를 새로 만들었다. (넘어온 list 값이 과도하기 때문에)
		List<MemberRtnDto> resultList = new ArrayList<MemberRtnDto>();
		
		//이전 목적 : 반드시 필요한 Data만 보여준다. (외부 노출 최대한 금지)
		//향상형 for문을 이용
		for(Member member: fineMembers) {
			//아래 문이 생성자가 없이도 잘 실행되는 이유 -> MemberRtnDto에 @AllArgsConstructor를 걸어주었기 때문에
			MemberRtnDto memberRtnDto = new MemberRtnDto(member.getName(), member.getSal());
			System.out.println("/restApi/v21/members getName -> "+memberRtnDto.getName());
			System.out.println("/restApi/v21/members getSal -> "+memberRtnDto.getSal());
			resultList.add(memberRtnDto);
		}
		System.out.println("/restApi/v21/members resultList.size()"+resultList.size());
		return new Result(resultList.size(), resultList);
	}
	
	//Good API 람다버전
	//목표 : 이름 & 급여 만 전송
	@GetMapping("/restApi/v22/members")
	public Result membersVer22() {
		List<Member> fineMembers = memberService.getListAllMember();
		System.out.println("JpaRestApiController restApi/v22/members findMembers.size()-> "
																	+fineMembers.size());
		// 자바 8에서 추가한 스트림(Streams)은 람다(->)를 활용할 수 있는 기술 중 하나
		//람다 형식 : 화살표 (->) 이용하는 것, stream()을 써 주어야지만 가능
		List<MemberRtnDto> memberCollect = 
				fineMembers.stream()
				//				m이라해도 되고 member라고 해도 된다.
				//				m이 있는 만큼 향상형 for문처럼 돌아가는 것
				//			하지만 map은 list로 수집을 하지는 못한다. 갖고만 있게 하는 로직
						   .map(m->new MemberRtnDto(m.getName(), m.getSal()))
						   //따라서 list로 만들어주는 작업을 해야한다.
						   .collect(Collectors.toList())
						   ;
		
		System.out.println("/restApi/v22/members memberCollect.size()-> "+memberCollect.size());
		//Result 생성자 (@AllArgsConstructor로 인해 생성자 정의 안해도 만들 수 있다.)
		//					 totCount				T data
		return new Result(memberCollect.size(), memberCollect);
	}
	
	@Data
	@AllArgsConstructor
	class Result<T> {
		private final int totCount;	//총 인원수 추가
		//들어오는 Data가 어떤 데이터형이든지 상관없이 T에 잡힌다. (Api의 경우는 T를 쓰는게 좋다.)
		private final T data;
	}
	
	//innerclass를 쓴 이유는 controller에서 값을 보내줄 때 딱 한번 쓰기 때문에
	//만일 이 로직을 service와 dao에도 넘겨준다면 domain(DTO)에 class를 만들어주면 된다.
	@Data
	//Data = getter + setter + Tostring
	//@AllArgsConstructor는 생성자
	@AllArgsConstructor
	class MemberRtnDto {
		//name과 sal만 뽑아서 data 전송해준다.
		//생성자가 없는 이유 -> AllArgsConstructor때문에!!
		//만약 하나를 final로 만들게 되면, @RequiredArgsConstructor로!!
		private String name;
		private Long   sal;
	}
	
	//Ver1. Bad
	@PostMapping("/restApi/v1/memberSave")
	// @RequestBody : Json(member)으로 온것을  --> Member member Setting
	public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
		System.out.println("JpaRestApiController /api/v1/memberSave member-> "+member);
		System.out.println("JpaRestApiController /api/v1/memberSave member.getId()-> "+member.getId());
		log.info("member.getName()-> {}.",member.getName());
		log.info("member.getSal()-> {}.", member.getSal());
		
		Long id = memberService.saveMember(member);
		//그냥 보내면 String으로 보내지지만(id), 생성자로 보내기 위해서 새로 만들어서 보내야 Json 으로 보내진다.
		return new CreateMemberResponse(id);
	}
	
	//Ver2.
	// 목적  : Entity Member member --> 직접 화면이나 API위한 Setting 금지
	// 예시  : @NotEmpty  -->	@Column(name = "userName")
	@PostMapping("/restApi/v2/memberSave")
	// @RequestBody : Json(member)으로 온것을  --> Member member Setting
	public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest cMember) {
		System.out.println("JpaRestApiController /api/v2/memberSave cmember-> "+cMember);
		log.info("cmember.getName()-> {}.",cMember.getName());
		log.info("cmember.getSal()-> {}.", cMember.getSal());
		
		Member member = new Member();
		member.setName(cMember.getName());
		member.setSal(cMember.getSal());
		
		Long id = memberService.saveMember(member);
		//그냥 보내면 String으로 보내지지만(id), 생성자로 보내기 위해서 새로 만들어서 보내야 Json 으로 보내진다.
		return new CreateMemberResponse(id);
	}
	
	/*
	 *   단일 Id 조회 API
	 *   URI 상에서 '{ }' 로 감싸여있는 부분과 동일한 변수명을 사용하는 방법
	 *   해당 데이터가 있으면 업데이트를 하기에 
	 *  GET요청이 여러번 실행되어도 해당 데이터는 같은 상태이기에 멱등
	 */
	//BadApi (이유: 보안문제, 전부 다 보내줘서!!)
	@GetMapping("/restApi/v15/members/{id}")
	public Member membersVer15(@PathVariable("id") Long id) {
		System.out.println("JpaRestApiController restApi/v15/members id-> "+id);
		Member findMember = memberService.findByMember(id);
		System.out.println("JpaRestApiController restApi/v15/members findMember->"+findMember);
		
		return findMember;
	}

	@Data
	static class CreateMemberRequest {
		@NotEmpty
		private String name;
		private Long sal;
	}
	
	@Data
	@RequiredArgsConstructor
	class CreateMemberResponse {
		private final Long id;
		//위에 @RequiredArgsConstructor를 썼기 때문에 생략해도 된다.
//		public CreateMemberResponse(Long id) {
//			this.id = id;
//		}
	}
	
	 @PutMapping("/restApi/v21/members/{id}")
	 public UpdateMemberResponse updateMemberV21(@PathVariable("id") Long id,
			                                     @RequestBody @Valid UpdateMemberRequest uMember) {
		 System.out.println("JpaRestApiController updateMemberV21 id->"+id);
		 System.out.println("JpaRestApiController updateMemberV21 uMember->"+uMember);
		 memberService.updateMember(id, uMember.getName(), uMember.getSal());
		 Member findMember = memberService.findByMember(id);
		 return new UpdateMemberResponse(findMember.getId(),findMember.getName(), findMember.getSal() );
	 }

	
//	@PutMapping("/restApi/v21/members/{id}")
//	public UpdateMemberResponse updateMemberV21(@PathVariable("id") Long id,
//												@RequestBody @Valid UpdateMemberRequest uMember) {
//		System.out.println("JpaRestApiController updateMemberV21 start...");
//		System.out.println("JpaRestApiController updateMemberV21 id ->"+id);
//		System.out.println("JpaRestApiController updateMemberV21 uMember ->"+uMember);
//		memberService.updateMember(id, uMember.getName(), uMember.getSal());
//		Member findMember = memberService.findByMember(id);
//		return new UpdateMemberResponse(findMember.getId(), findMember.getName(), findMember.getSal());
//	}
	
	@Data
	static class UpdateMemberRequest {
		private String name;
		private Long sal;
	}
	
	@Data
	@AllArgsConstructor
	class UpdateMemberResponse {
		private Long id;
		private String name;
		private Long sal;
	}
}
