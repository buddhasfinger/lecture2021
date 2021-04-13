package com.library.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.dto.HopeBookBoardDto;
import com.library.dto.lMemberDto;
import com.library.service.hopeboard.HopeBookBoardService;

@Controller
public class HopeBookBoardController {

	
	@Autowired
	HopeBookBoardService bs;
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	
	@RequestMapping("/hopebookboard/hopebook_list")
	public String hopebook_list(@RequestParam @Nullable String page,
			@RequestParam @Nullable String category,
			@RequestParam @Nullable String search, 
			Model model) {
		System.out.println("page :"+page);
		Map<String, Object> map = bs.hboardListAll(page,category,search);
		model.addAttribute("map",map);
		
		return "/hopebookboard/hopebook_list";
	}
	
	@RequestMapping("/hopebookboard/content_view")
	public String content_view(@RequestParam @Nullable String page,
			@RequestParam @Nullable String category,
			@RequestParam @Nullable String search, 
			@RequestParam @Nullable String hb_Seq, 
			Model model) {
		map = bs.boardContent_view(hb_Seq, page, category, search);
		model.addAttribute("map",map);
		
		return "hopebookboard/content_view";
	}
	
	@RequestMapping("/write_view")
	public String write_view(@RequestParam String hb_User,Model model) {
		model.addAttribute("hb_User",hb_User);
		
		return "write_view";
	}
	
	@RequestMapping("/write")
	public String  write(HopeBookBoardDto boardDto) {
		System.out.println("hb_User "+boardDto.getHb_User());
		System.out.println("hb_Title "+boardDto.getHb_Title());
		bs.BoardWrite(boardDto);
		return "redirect:/list";
	}

	@RequestMapping("/hrwrite")  
	@ResponseBody
	public Map<String, Object> hrwrite(HttpServletRequest request,String hrw_Content,String hrw_User,String hrw_Group) {
		System.out.println("hrw_Content "+hrw_Content);
		System.out.println("hrw_User "+hrw_User);
		System.out.println("hrw_Group "+hrw_Group);
		//bs.ReplyWrite(hrw_Content,hrw_User,hrw_Group);
		map.put("hrw_Content", hrw_Content);
		map.put("hrw_User", hrw_User);
		map.put("hrw_Group", hrw_Group);
		
		return map;
	}
	
	
	
}
