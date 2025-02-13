package com.oracle.oBootMybatis01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SocketController {
	
	@RequestMapping("/chat")
	public ModelAndView chat() {
		//ModelAndView 는 Model과  view를 같이 쓴다.
		System.out.println("SocketController chat Start...");
		ModelAndView mv = new ModelAndView();
		
		//			viewResolver로 인해서
		mv.setViewName("chatView");
		return mv;
	}
	
}
