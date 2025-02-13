package com.oracle.oBootJpa02.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member2")
//@Getter
//@Setter
//@ToString
//@Getter + @Setter + @ToString = @Data
@Data
@SequenceGenerator(
					name 		 = "member_seq_gen",		//Seq 객체
					sequenceName = "member_seq_generator",	//Seq DB -> DB는 얘로 만들어줌
					initialValue = 1,
					allocationSize = 1
				  )
public class Member {
	@Id
	@GeneratedValue(
					strategy = GenerationType.SEQUENCE,
					generator = "member_seq_gen"	//Seq 객체
					)
	@Column(name = "member_id", precision = 10)
	private Long id;
	@Column(name = "username", length = 50)
	private String name;
	private Long sal;
	
	//관계 설정
//	@ManyToOne(fetch = FetchType.LAZY)
	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;
	
	// 실제 Column X --> Buffer 용도
	@Transient //-> dto, entity 단위로 부를 수는 있다.
	private String teamname;
	
	@Transient
	private Long teamid;
}
