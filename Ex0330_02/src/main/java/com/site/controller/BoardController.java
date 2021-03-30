package com.site.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {
	
	@RequestMapping("/ajax01")
	public String ajax01() {
		
		return"ajax01";
	}
	@RequestMapping("/test")
	public String test() {
		
		return"test";
	}
	@ResponseBody
	@RequestMapping("/ajax_ok")
	public Map ajax_ok(String rname ,String rcontent) {
		System.out.println("이름 :"+ rname+",내용 :" + rcontent);
		Map map = new HashMap<String, Object>();
		map.put("rname", rname);
		map.put("rcontent", rcontent);
		return map;
	}
	
}
