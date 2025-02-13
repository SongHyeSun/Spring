package com.oracle.oBootJpaApi01.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "member5")
//과제
//1. Sequence
//	1) 객체 name : member_seq_gen5
//	2) DB name : member_seq_generator5
// 	3) 초기값 1, 할당 1씩 증가
@SequenceGenerator(
					name = "member_seq_gen5",
					sequenceName = "member_seq_generator5",
					initialValue = 1,
					allocationSize = 1
					)
public class Member {
	
//	2. Id에 PK 잡아주고, member_seq_gen5를 이용해서 , name은 member_id로
	@Id
	@GeneratedValue(
					strategy = GenerationType.SEQUENCE,
					generator = "member_seq_gen5"
					)
	@Column(name = "member_id")
	private Long id;
	
//	3. name의 이름은 userName
	@Column(name = "userName")
	private String name;
	private Long sal;
	
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
}
