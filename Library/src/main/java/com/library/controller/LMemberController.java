package com.library.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.dto.lMemberDto;
import com.library.service.lmember.LMemberService;

@Controller
public class LMemberController {
	
	@Autowired
	LMemberService memberService;
	
	@RequestMapping("/lmember/login")
	public String login() {
		return "/lmember/login";
	}
	
	@RequestMapping("/loginCheck")
	@ResponseBody
	public Map login_check(lMemberDto dto,HttpServletRequest request) {
		
		Map map = memberService.logincheck(dto);
		if((int)map.get("loginCheck")==1) {
			HttpSession session= request.getSession();
			session.setAttribute("session_flag", "success");
			session.setAttribute("session_id", ((lMemberDto) map.get("memberDto")).getLm_id());
			session.setAttribute("session_nName", ((lMemberDto) map.get("memberDto")).getLm_name());
			System.out.println("세션 저장 성공? "+session.getAttribute("session_flag"));
		}
		map.put("loginCheck", (int)map.get("loginCheck"));
		
		
		return map; 
	}
	
	@RequestMapping("/lmember/logout")
	public String logout() {
		return "/lmember/logout";
	}
}
