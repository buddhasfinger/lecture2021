package com.library.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.dto.RoomBookingDto;
import com.library.service.study.StudyService;

@Controller
public class StudyController {
	
	@Autowired
	StudyService studyService;
	
	Map<String,Object> map;
	List list = new ArrayList();
	int rs=0 ;
	
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	@RequestMapping("/study/studyRoom")
	public String studyRoom() {
		return "/study/studyRoom";
	}
	@RequestMapping("/study/roomTime")
	public String roomTime() {
		return "/study/roomTime";
	}

	@RequestMapping("/study/dateCheck")
	@ResponseBody
	public Map dateCheck(RoomBookingDto roombookingDto) {
		Map map = new HashMap<String, Object>();
		System.out.println("date :" +roombookingDto.getRb_date());
		System.out.println("roomNum :" +roombookingDto.getSr_id());
		
		map.put("rs",studyService.dateCheck(roombookingDto.getSr_id(),roombookingDto.getRb_date()));
		
		return map;
	}
	
	@RequestMapping("/study/roomBookingCheck")
	@ResponseBody
	public Map roomBookingCheck(RoomBookingDto roombookingDto ) {
		Map map = new HashMap<String, Object>();
		System.out.println("time :" + roombookingDto.getRb_time());
		System.out.println("roomNum :" +roombookingDto.getSr_id());
		System.out.println("예약자id :" +roombookingDto.getLm_id());

		studyService.roomBooking(roombookingDto);
		//studyService.roomBooking(roombookingDto.getSr_id(),roombookingDto.getRb_date(),roombookingDto.getRb_time());
		
		map.put("rs","예약이 완료됐습니다.마이페이지에서 예약정보를 확인하세요." )	;
		
		return map;
	}
	
//	@RequestMapping("/study/roomBookingCheck")
//	@ResponseBody
//	public Map roomBookingCheck(@RequestParam("timeChoice") String rb_time,@RequestParam("roomNum") String sr_id,@RequestParam("date") String rb_date) {
//		Map map = new HashMap<String, Object>();
//		System.out.println("date :" +rb_time);
//		System.out.println("roomNum :" +sr_id);
//		
//		
//		
//		map.put("rs","예약이 완료됐습니다.마이페이지에서 예약정보를 확인하세요." )	;
////		map.put("date", rb_date);
////		map.put("roomNum", sr_id);
//		
//		return map;
//	}
	
}
