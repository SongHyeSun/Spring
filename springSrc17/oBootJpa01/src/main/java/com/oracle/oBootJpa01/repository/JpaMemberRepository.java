package com.oracle.oBootJpa01.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootJpa01.domain.Member;

import jakarta.persistence.EntityManager;

@Repository
public class JpaMemberRepository implements MemberRepository {
	// JPA DML --> EntityManager 필수  ***
	private final EntityManager em;
	
	@Autowired
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member memberSave(Member member) {
		// JPA 에서는 DTO가 아니라 ENTITY ->
		//persist를 하면 내부에서 알아서 insert를 만들어준다. (이게 진짜 ORM)
		em.persist(member);
		System.out.println("JpaMemberRepository memberSave member After...");
		return member;
	}

	@Override
	public List<Member> findAllMember() {
		//orm : object를 RDB로 mapping
		//									여기서 Member는 객체 Member!!!! TBL이 아니다, Member를 class 단위로!!
		//									따라서 TBL 처럼 대소문자 구분 안해도 되지 않다.!!
		List<Member> memberList = em.createQuery("SELECT m from Member m", Member.class).getResultList();
		System.out.println("JpaMemberRepository findAllMember memberList.size()->"+memberList.size());
		return memberList;
	}

	@Override
	public List<Member> findByNames(String searchName) {
		String pname = searchName + '%';
		System.out.println("JpaMemberRepository findByNames pname->"+pname);
								//														Like 뒤의 name 은 column의 이름
		List<Member> memberList = em.createQuery("SELECT m FROM Member m WHERE name LIKE :name", Member.class)
														//pname 은 %로 불러온 조건
									.setParameter("name", pname)
									.getResultList();
		System.out.println("JpaMemberRepository memberList.size()"+memberList.size());
		return memberList;
	}

}
