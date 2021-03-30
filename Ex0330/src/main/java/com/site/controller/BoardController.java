package com.site.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.dto.MemberDto;
import com.site.service.MemberService;

@Controller
public class BoardController {
	
	@Autowired
	MemberService member;
	
	Map map = new HashMap<String, Object>();
	
	//ResponseBody를 해줘야지 데이터만 보내는걸 찍어줄수있다.
//	@RequestMapping("/index")
//	@ResponseBody
//	public Map<String, Object> index() {
//		
//		Map map = new HashMap<String,Object>();
//		map.put("str1","홍길동");
//		map.put("str2","이순신");
//		return map;
//	}
	@RequestMapping("/form")
	public String form() {
		
		return "form";
	}
	@RequestMapping("/list")
	public String list() {
		
		return "list";
	}
	@RequestMapping("/index")
	public String index() {
		
		return "index";
	}
	@RequestMapping("/memberInfo")
	public String memberInfo(Model model) {
		map= member.MemberListAll();
		model.addAttribute("map",map);
		return "memberInfo";
	}
//	@RequestMapping("/formOk")
//	public String formOk(HttpServletRequest request,Model model) {
//		String id= request.getParameter("id");
//		String pw= request.getParameter("pw");
//		
//		model.addAttribute("id",id);
//		model.addAttribute("pw",pw);
//		return "formOk";
//	}
//	@RequestMapping("/formOk2")
//	public String formOk2(@RequestParam("id") String id,
//			@RequestParam("pw") String pw,Model model) {
//		
//		
//		model.addAttribute("id",id);
//		model.addAttribute("pw",pw);
//		return "formOk2";
//	}
	@RequestMapping("/formOk")
	public String formOk(MemberDto memberDto,Model model) {
		
		int result = member.loginCheck(memberDto);
		
		model.addAttribute("memberDto",memberDto); //입력한 id,pw
		model.addAttribute("result",result); //확인결과 값
		
		return "formOk";
	}
}
