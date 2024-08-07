package com.oracle.oBootJpaApi01.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "team5")
//과제
//1. Sequence
//	1) 객체 name : team_seq_gen5
//	2) DB name : team_seq_generator5
//	3) 초기값 1, 할당 1씩 증가
@SequenceGenerator(
					name = "team_seq_gen5",
					sequenceName = "team_seq_generator5",
					initialValue = 1,
					allocationSize = 1
					)
public class Team {
//	2. Id에 PK 잡아주고, team_seq_gen5를 이용해서
	@Id
	@GeneratedValue(
					strategy = GenerationType.SEQUENCE,
					generator = "team_seq_gen5"
					)
	//			카멜 표기법 : teamId, myValue
	private Long teamId;
	@Column(name = "teamName", precision = 50)
//	3. name의 이름은 teamName, size 50
	private String name;
}
