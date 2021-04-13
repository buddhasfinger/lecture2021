package com.site.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.site.dto.CommentDto;
import com.site.service.EventService;

@Controller
public class EventController {
	
	@Autowired
	EventService eventService;
	Map<String , Object> map;
	
	@RequestMapping("/event/event")
	public String event() {
		return "event/event";
	}
	@RequestMapping("/event/event_view")
	public String event_view() {
		return "event/event_view";
	}
	@RequestMapping("/event/commentWrite_check")
	@ResponseBody
	public Map<String,Object> commentWrite_check(CommentDto commentDto) {
		map = new HashMap<String, Object>();
		System.out.println("commentDto:" +commentDto.getId());
		
		CommentDto dto = eventService.commentWrite_check(commentDto);
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		String commentDate = sdf.format(System.currentTimeMillis());
		
		//db에서 데이터를 받아올것
		map.put("dto", dto);
		return map;
	}
	
}
