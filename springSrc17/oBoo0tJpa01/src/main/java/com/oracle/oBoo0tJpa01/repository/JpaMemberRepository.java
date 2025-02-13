package com.oracle.oBoo0tJpa01.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBoo0tJpa01.domain.Member;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> findByNames(String searchName) {
		// TODO Auto-generated method stub
		return null;
	}

}
