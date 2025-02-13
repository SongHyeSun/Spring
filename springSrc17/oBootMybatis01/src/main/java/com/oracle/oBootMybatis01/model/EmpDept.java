package com.oracle.oBootMybatis01.model;

import lombok.Data;

@Data
public class EmpDept {
	//두 TBL이 많다는 전제하에  Join 목적으로 만든 DTO
	
	//EMP용
	private int empno;
	private String ename;
	private String job;
	private int mgr;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;
	
	//Dept 용 (많다는 가정 하에)
	private String dname;
	private String loc;
}
