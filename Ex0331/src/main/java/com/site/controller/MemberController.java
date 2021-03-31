package com.site.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.dto.MemberDto;
import com.site.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/member/login")
	public String login() {
		return "member/login";
	}
	@RequestMapping("/member/logout")
	public String logout() {
		
		return "member/logout";
	}
	
	@RequestMapping("/loginCheck")
	@ResponseBody
	public Map loginCheck(HttpServletRequest request, String id, String pw) {
		
		Map map = memberService.loginCheck(id, pw);
		
		if((int)map.get("loginCheck")==1) {
			HttpSession session= request.getSession();
			session.setAttribute("session_flag", "success");
			session.setAttribute("session_nName", ((MemberDto) map.get("memberDto")).getNname());
		
		}
		map.put("loginCheck", (int)map.get("loginCheck"));
		return map;
		
	}
//	@RequestMapping("/member/loginOk")
//	public String loginOk(HttpServletRequest request, MemberDto dto,Model model) {
//		Map map = memberService.loginCheck(dto);
//		String url="redirect:/member/login"; //로그인 성공시
//		
//		if((int)map.get("loginCheck")==1) {
//			HttpSession session= request.getSession();
//			session.setAttribute("session_flag", "success");
//			MemberDto memberDto = (MemberDto) map.get("memberDto");
//			session.setAttribute("session_nName", memberDto.getNname());
//			url="board/index";
//		}
//		
//		//model.addAttribute("loginCheck",loginCheck);
//		return url;
//	}
}
