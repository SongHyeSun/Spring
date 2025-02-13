package com.oracle.oBootHello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.oBootHello.dto.Emp;

@Controller
public class HelloController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	//prefix -> templates
	//suffix -> .html
	@RequestMapping("hello")
	public String hello(Model model) {
		System.out.println("HelloController hello start...");
		logger.info("start...");
		model.addAttribute("parameter","boot start...");
		return "hello";
		// (DispatcherServlet이 호출)viewResolver--> (prefix)template/ + hello + html
		// view 단으로 완성해서!
	}

	//responseBody는 Httpresult , responsebody를 붙히지 않으면, 자동으로 suffix를 붙혀주어서 html
	@ResponseBody
	//GetMapping은 RequestMapping의 자손 -> get인지 post인지 고를 수 있음
	@GetMapping("ajaxString")
	public String ajaxString(@RequestParam("ajaxName") String aName) {
		System.out.println("HelloController ajaxString aName->"+aName);
		return aName;
	}
	
	@ResponseBody
	//보통 URL과 annotation 명을 맞춰준다.
	@GetMapping("ajaxEmp")
	public Emp ajaxEmp(@RequestParam("empno") String empno, 
					   @RequestParam("ename") String ename) {
		System.out.println("HelloController ajaxEmp empno->"+empno);
		System.out.println("HelloController ajaxEmp ename->"+ename);
		logger.info("ename-> {}",ename);
		Emp emp = new Emp();
		emp.setEmpno(empno);
		emp.setEname(ename);
		
		//
		return emp;
	}
	
}