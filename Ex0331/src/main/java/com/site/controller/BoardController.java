package com.site.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.site.dto.BoardDto;
import com.site.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/board/delete")
	public String  delete(
			@RequestParam @Nullable String page,
			@RequestParam @Nullable String bid,
			@RequestParam @Nullable String category,
			@RequestParam @Nullable String search,Model model ) throws Exception {
		search= URLEncoder.encode(search,"utf-8");
		boardService.boardDelete(bid);
		
		model.addAttribute("map",map);
		return "redirect:/board/list?page="+page+"&category="+category+"&search="+search;
	}
	
	@RequestMapping("/board/reply")
	public String  reply(BoardDto boardDto,@RequestPart MultipartFile file,
			@RequestParam @Nullable String page,
			@RequestParam @Nullable String category,
			@RequestParam @Nullable String search,Model model ) throws Exception {
		search= URLEncoder.encode(search,"utf-8");
		boardService.boardReply(boardDto,file);
		
		model.addAttribute("map",map);
		return "redirect:/board/list?page="+page+"&category="+category+"&search="+search;
	}
	
	@RequestMapping("/board/reply_view")
	public String reply_view(@RequestParam @Nullable String page,
			@RequestParam @Nullable String category,
			@RequestParam @Nullable String search, 
			@RequestParam @Nullable String bid, Model model) {
		map = boardService.boardModify_view(bid, page, category, search);
		model.addAttribute("map",map);
		return "board/reply_view";
	}
	
	
	@RequestMapping("/board/modify")
	public String  modify(@RequestParam @Nullable String page,
			@RequestParam @Nullable String category,
			@RequestParam @Nullable String search, 
			BoardDto boardDto,@RequestPart MultipartFile file,Model model) throws Exception {
		
		boardService.BoardModify(boardDto,file);
		search= URLEncoder.encode(search,"utf-8");
		
		return "redirect:/board/list?page="+page+"&category="+category+"&search="+search;
	}
	
	@RequestMapping("/board/modify_view")
	public String modify_view(@RequestParam @Nullable String page,
			@RequestParam @Nullable String category,
			@RequestParam @Nullable String search, 
			@RequestParam @Nullable String bid, 
			Model model) {
		map = boardService.boardModify_view(bid, page, category, search);
		model.addAttribute("map",map);
		return "board/modify_view";
	}
	
	@RequestMapping("/board/content_view")
	public String content_view(@RequestParam @Nullable String page,
			@RequestParam @Nullable String category,
			@RequestParam @Nullable String search, 
			@RequestParam @Nullable String bid, 
			Model model) {
		map = boardService.boardContent_view(bid, page, category, search);
		model.addAttribute("map",map);
		return "board/content_view";
	}
	@RequestMapping("/board/write_view")
	public String write_view() {
		return "board/write_view";
	}
	@RequestMapping("/board/write")
	public String  write(BoardDto boardDto,@RequestPart MultipartFile file) {
		boardService.BoardWrite(boardDto,file);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/board/write1")
	@ResponseBody
	public String  write1(BoardDto boardDto,@RequestPart MultipartFile file) {
		boardService.BoardWrite(boardDto,file);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/board/list")
	public String list(@RequestParam @Nullable String page,
			@RequestParam @Nullable String category,
			@RequestParam @Nullable String search, 
			Model model) {
		System.out.println("page :"+page);
		Map<String, Object> map = boardService.boardListAll(page,category,search);
		model.addAttribute("map",map);
		
		return "board/list";
	}
	
}
