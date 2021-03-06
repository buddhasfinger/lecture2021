package com.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.site.dto.BoardDto;
import com.site.service.BoardService;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;


@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/page1")
	public String page1() {
		return "page1";
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request,Model model) {
		int page = Integer.parseInt(request.getParameter("page"));
		String category = request.getParameter("category");
		String search = request.getParameter("search");

		model.addAttribute("page",page);
		model.addAttribute("category",category);
		model.addAttribute("search",search);
		
		return "list";
	}
	
	@RequestMapping("/list2/{page}/{category}/{search}")
	public String list2(@PathVariable("page") String page
			, @PathVariable("category") String category
			,@PathVariable("search") String search,Model model) {
		
		model.addAttribute("page",page);
		model.addAttribute("category",category);
		model.addAttribute("search",search);
		
		return "list2";
	}
	@RequestMapping("/list3/{page}/{category}/{search}")
	public String list3(@PathVariable("page") String page
			, @PathVariable("category") String category
			,@PathVariable("search") String search,Model model) {
		
		HashMap<String, Object> map = boardService.boardList(page,category,search);
		
		model.addAttribute("map",map);
		
		return "list3";
	}
	@RequestMapping("/form")
	public String form() {
		return "form";
	}
	@RequestMapping("/form2/{uploadFileName}")
	public String form2(@PathVariable("uploadFileName") String uploadFileName,Model model) {
		//	<form>
		//1. HttpServletRequest request -> request.getParameter("");
		//2. @RequestParam("admin") String admin
		//3. @RequestPart("file") Multipartfile file
		//4. BoardDto dto -> dto.getId();
		//	<a??????>
		//1.PathVariable?????? : @PathVariable("admin") String admin
		//2.???????????? ?????? : form?id=admin -> request.getparameter("admin");
		System.out.println("uploadFileName:" +uploadFileName);
		model.addAttribute(uploadFileName);
		return "form2";
	}
	
	@RequestMapping("/form_ok")
	public String form_ok(BoardDto boarddto,@RequestPart MultipartFile file,Model model) {
		
		HashMap<String, Object> map=	boardService.boardFileWrite(boarddto,file);
		model.addAttribute("map",map);
		return "form_ok";
	}
	
}
