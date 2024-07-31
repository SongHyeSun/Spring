package com.oracle.oBootDbConnect.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oracle.oBootDbConnect.domain.Member7;

//@Repository
public class MemoryMemberRepository implements MemberRepository {
	
	//memory에 저장하기 위해
	private static Map<Long, Member7> store = new HashMap<Long, Member7>();
	private static Long sequence = 0L;
	
	@Override
	public Member7 save(Member7 member7) {
		System.out.println("MemoryMemberRepository save start...");
		member7.setId(++sequence);
		//			primary key		DTO
		store.put(member7.getId(), member7);
		System.out.println("MemoryMemberRepository sequence ->"+sequence);
		System.out.println("MemoryMemberRepository member7.getName()->"+member7.getName());;
		
		return member7;
	}

	@Override
	public List<Member7> findAll() {
		System.out.println("MemoryMemberRepository findAll start...");
		
		List<Member7> listMember = new ArrayList<>(store.values());
		System.out.println("MemoryMemberRepository findAll slistMember.size()=>"+listMember.size());
		return listMember;
		
//		이런식으로 한번에 표현할 수 있다. -> value값만 필요하면, store.values()만 해주면 되고
//		System.out.println("MemoryMemberRepository findAll start...");
//		return new ArrayList<>(store.values());
	}

}
