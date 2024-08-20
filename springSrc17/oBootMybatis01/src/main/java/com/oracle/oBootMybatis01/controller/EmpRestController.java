package com.oracle.oBootMybatis01.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.SampleVO;
import com.oracle.oBootMybatis01.service.EmpService;

import lombok.RequiredArgsConstructor;

//@Controller + @ResponseBody
@RestController
@RequiredArgsConstructor
public class EmpRestController {
	
	private final EmpService es;
	
	@RequestMapping("/helloText")
	public String helloText() {
		System.out.println("EmpRestController helloText Start...");
		String hello = "안녕";
		//	   StringConverter
		return hello;
	}
	
	// http://jsonviewer.stack.hu/
	@RequestMapping(value = "/sample/sendVO2")
	public SampleVO sendVO2(Dept dept) {
		System.out.println("EmpRestController SampleVO Start....");
		System.out.println("@RestController SampleVO dept.getDeptno()-> "+dept.getDeptno());
		SampleVO vo = new SampleVO();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(dept.getDeptno());
		
		System.out.println("EmpRestController SampleVO End....");
		//객체가 return
		return vo;
	}
	
	@RequestMapping("/sendVO3")
	public List<Dept> sendVO3() {
		System.out.println("EmpRestController @RestController sendVO3 START..");
		List<Dept> deptList = es.deptSelect();
		return deptList;
	}
	
	//결과 text로 보내지는 것
	@RequestMapping("/empnoDelete")
	public String empnoDelete(Emp emp) {
		System.out.println("EmpRestController @RestController empnoDelete START..");
		System.out.println("EmpRestController @RestController empnoDelete emp-> "+emp);
		int delStatus = es.deleteEmp(emp.getEmpno());
		String delStatusStr = Integer.toString(delStatus);
		return delStatusStr;
	}
	
	//결과 --> 객체로 전달 (객체는 DTO로 or Map으로!)
	@RequestMapping("/empnoDelete03")
	public Map<String, Object> empnoDelete03(Emp emp) {
		System.out.println("EmpRestController @RestController empnoDelete03 START..");
		System.out.println("EmpRestController @RestController empnoDelete03 emp-> "+emp);
		int delStatus = es.deleteEmp(emp.getEmpno());
//		String delStatusStr = Integer.toString(delStatus);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("delStatus", delStatus);
		return resultMap;
	}
}
