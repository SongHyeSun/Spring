package com.oracle.oBoo0tJpa01.repository;

import java.util.List;

import com.oracle.oBoo0tJpa01.domain.Member;

public interface MemberRepository {
	Member 			memberSave(Member member);
	List<Member> 	findAllMember();
	List<Member> 	findByNames(String searchName);
}