package com.oracle.oBootHello.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oracle.oBootHello.dto.Member1;

public class MemoryMemberRepository implements MemberRepository {

	private static Map<Long, Member1> store = new HashMap<Long, Member1>();
	//sequence 발행 -> oracle sequence같은 !
	private static Long sequence = 0L;
	
	@Override
	public Member1 save(Member1 member1) {
		member1.setId(++sequence);
		// store를 보내서 보여주면 된다.
		store.put(member1.getId(), member1);
		System.out.println("MemoryMemberRepository sequence->"+sequence);
		System.out.println("MemoryMemberRepository member1.getName()->"+ member1.getName());
		//member1 객체를 하나씩 추가해서 store에 저장해줄 것이다!
		return member1;
	}

	@Override
	public List<Member1> findAll() {
		System.out.println("MemoryMemberRepository findAll start...");
		//store의 value(Member1)
		List<Member1> listMember = new ArrayList<>(store.values());
		System.out.println("MemoryMemberRepository findAll slistMember.size()=>"+listMember.size());
		return listMember;
	}

}
