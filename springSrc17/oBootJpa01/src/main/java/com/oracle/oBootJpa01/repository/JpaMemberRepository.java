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
		em.persist(member);
		System.out.println("JpaMemberRepository memberSave member After...");
		return member;
	}

	@Override
	public List<Member> findAllMember() {
		List<Member> memberList = em.createQuery("SELECT m from Member m", Member.class).getResultList();
		System.out.println("JpaMemberRepository findAllMember memberList.size()->"+memberList.size());
		return memberList;
	}

	@Override
	public List<Member> findByNames(String searchName) {
		String pname = searchName + '%';
		System.out.println("JpaMemberRepository findByNames pname->"+pname);
		List<Member> memberList = em.createQuery("SELECT m FROM Member m WHERE name LIKE :name", Member.class)
									.setParameter("name", pname)
									.getResultList();
		System.out.println("JpaMemberRepository memberList.size()"+memberList.size());
		return memberList;
	}

}
