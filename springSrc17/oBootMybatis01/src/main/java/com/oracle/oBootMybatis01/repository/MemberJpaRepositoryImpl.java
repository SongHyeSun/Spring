package com.oracle.oBootMybatis01.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.domain.Member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepositoryImpl implements MemberJpaRepository {
	
	private final EntityManager em;

	@Override
	public Member save(Member member) {
		System.out.println("MemberJpaRepositoryImpl save start...");
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {
		System.out.println("MemberJpaRepositoryImpl findAll start...");
		List<Member> memberList = null;
		memberList = em.createQuery("SELECT m FROM Member m",Member.class).getResultList();
		System.out.println("MemberJpaRepositoryImpl findAll memberList.size()-> "+memberList.size());
		return memberList;
	}
	

	@Override
	public Optional<Member> findById(Long memberId) {
		System.out.println("MemberJpaRepositoryImpl findById start...");
		Member member = em.find(Member.class, memberId);
		return Optional.ofNullable(member);
	}

	@Override
	public void updateByMember(Member member) {
		//1. update
		//merge --> 현재 setting 된것만 수정, 나머지는 null로 setting
		System.out.println("MemberJpaRepositoryImpl updateByMember update1 start...");
		em.merge(member);
		
		//2. update
//		System.out.println("MemberJpaRepositoryImpl updateByMember update2 start...");
//		Member member3 = em.find(Member.class, member.getId());
//		if (member3 != null) {
//			//회원저장
//			member3.setId(member.getId());
//			member3.setName(member.getName());
//			System.out.println("MemberJpaRepositoryImpl updateByMember Update...");
//		} else {
//			System.out.println("MemberJpaRepositoryImpl updateByMember No Exist..");
//		}
		System.out.println("MemberJpaRepositoryImpl updateByMember after...");
	}

}
