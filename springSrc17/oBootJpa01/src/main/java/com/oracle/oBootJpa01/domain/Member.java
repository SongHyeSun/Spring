package com.oracle.oBootJpa01.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Entity를 쓰려면 primary key 를 반드시 지정해주어야 한다. (@Id)
@Entity
@Table(name = "member2")
@Getter
@Setter
@ToString
//@Data -> @Getter, Setter, ToString을 한번에 Data annotation으로 쓸 수 있다.!
public class Member {
	@Id
	private Long id;
	private String name;
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
	
//	@Override
//	public String toString() {
//		String returnStr = "[id:"+this.id+", name: "+this.name + "]";
//		return returnStr;
//	}
}
