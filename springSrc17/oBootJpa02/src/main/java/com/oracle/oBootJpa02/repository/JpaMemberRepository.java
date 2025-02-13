package com.oracle.oBootJpa02.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.domain.Team;

import jakarta.persistence.EntityManager;

@Repository
public class JpaMemberRepository implements MemberRepository {
	
	private final EntityManager em;
	
	@Autowired
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member memberSave(Member member) {
		System.out.println("JpaMemberRepository memberSave  team start...");
		// 1. 팀 저장
		Team team = new Team();
		team.setName(member.getTeamname());
		em.persist(team);
		System.out.println("JpaMemberRepository memberSave  team end...");

		//2. 회원 저장
		System.out.println("JpaMemberRepository memberSave  member start...");
		member.setTeam(team);
		em.persist(member);
		System.out.println("JpaMemberRepository memberSave  member end...");
		return member;
	}

	@Override
	public List<Member> findAll() {
										//jpql의 문법		//Member는 객체를 찾아가는 것! -> TBL 명을 넣으면 안된다.
		List<Member> memberList = em.createQuery("SELECT m from Member m", Member.class)
									.getResultList();
									//각각으로 분해되어있는 데이터를 List로 만들어주는 것!!
		return memberList;
	}

	@Override
	public Member findByMember(Long memberId) {
		Member member = em.find(Member.class, memberId);
		return member;
	}

	@Override
	public int updateByMember(Member member) {
		int result = 0;
		System.out.println("JpaMemberRepository updateByMember 1 member->"+member);
		Member member3 = em.find(Member.class, member.getId());
		//존재하면 수정, 그렇지 않으면 insert
		if (member3 != null) {
			//팀 저장
			System.out.println("JpaMemberRepository updateByMember member.getTeamid()->" + member.getTeamid());
			Team team = em.find(Team.class, member.getTeamid());
			if (team != null) {
				team.setName(member.getTeamname());
				em.persist(team);
			}
			
			//회원 저장
			member3.setTeam(team);
			member3.setName(member.getName());
			em.persist(member3);
			result = 1;
		} else {
			result = 0;
			System.out.println("JpaMemberRepository updateByMember No Exist...");
		}
		return result;
	}

	@Override
	public List<Member> findByMember(String searchName) {
		String pname = searchName + '%';
		System.out.println("JpaMemberRepository findByMember pname-> "+pname);
		
		List<Member> memberList = em.createQuery("SELECT m FROM Member m WHERE name LIKE :name",Member.class)
									.setParameter("name", pname)
									.getResultList();
		System.out.println("JpaMemberRepository memberList.size()"+memberList.size());
		return memberList;
	}

	@Override
	public List<Member> findByMember(Long pid, Long psal) {	
		System.out.println("JpaMemberRepository findByMember pid-> "+pid);
		System.out.println("JpaMemberRepository findByMember psal-> "+psal);
		
		List<Member> memberList = em.createQuery("SELECT m FROM Member m WHERE id >= :id AND sal >= :sal",Member.class)
									.setParameter("id", pid)
									.setParameter("sal", psal)
									.getResultList();
		System.out.println("JpaMemberRepository memberList.size()"+memberList.size());
		return memberList;
	}

}
