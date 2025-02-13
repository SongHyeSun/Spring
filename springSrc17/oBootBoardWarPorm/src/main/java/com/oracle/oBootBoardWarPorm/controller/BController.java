package com.oracle.oBootBoardWarPorm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oBootBoardWarPorm.command.BExecuteCommand;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BController {
	private static final Logger logger = LoggerFactory.getLogger(BController.class);
	private final BExecuteCommand bExecuteService;
	
	//이걸 잡아준다는 것은 service가 bean으로 떠 있어야 한다는 것
	//bExecuteService로 instance 만들어놓았다는 의미
	@Autowired
	public BController(BExecuteCommand bExecuteService) {
		this.bExecuteService = bExecuteService;
	}
	
	//list.jsp에서 불러줌
	@RequestMapping("list")
	public String list(Model model) {
		logger.info("list start...");
		//callByReference model 객체 (주소값)
		bExecuteService.bListCmd(model);
		
		model.addAttribute("count","50");
		return "list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_ view start...");
		
		//Map 방식 -> key는 string, value는 객체!!로 돌려준다.
		model.addAttribute("request", request);
		bExecuteService.bContentCmd(model);
		
		return "content_view";
	}
	
	@RequestMapping(value = "/modify", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, Model model) {
		logger.info("modify start...");
		model.addAttribute("request", request);
		bExecuteService.bModifyCmd(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete() start...");
		
		model.addAttribute("request", request);
		bExecuteService.bDeleteCmd(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/write_view")
	public String write_view(HttpServletRequest request, Model model) {
		logger.info("write_view start...");
		
		return "write_view";
	}
	
	@PostMapping(value = "/write")
	public String write(HttpServletRequest request, Model model) {
		logger.info("write start..");
		
		model.addAttribute("request",request);
		bExecuteService.bWriteCmd(model);
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view start...");
		
		model.addAttribute("request", request);
		bExecuteService.bReplyViewCmd(model);
		return "reply_view";
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply()");
		
		model.addAttribute("request", request);
		bExecuteService.bReplyCmd(model);
		
		return "redirect:list";
	}
}
