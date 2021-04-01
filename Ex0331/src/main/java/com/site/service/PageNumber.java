package com.site.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.site.mapper.BoardMapper;

@Component
public class PageNumber {
	
	@Autowired
	BoardMapper boardMapper;
	
	//하단 넘버링 정보 메소드
	public Map<String, Object> PageNumber(int page, int limit,
			String category,String search) {
		Map map = new HashMap<String, Object>();
		int listCount = 0;
		if(category==null || category.equals("")) {
			// 전체리스트 개수메소드
			listCount = boardMapper.listCount();
		}else if(category.equals("title")) {
			// title검색 개수메소드
			listCount = boardMapper.listCountTitle(search);
		}else if(category.equals("content")) {
			// Content검색 개수메소드
			listCount = boardMapper.listCountContent(search);
		}else if(category.equals("all")) {
			listCount = boardMapper.listCountSearchAll(search);
		}
		
		// 최대 페이지 수
		int maxpage = (int) ((double) listCount / limit + 0.95);
		// 매 선택된 페이지 마다 제일 앞에 시작할 첫 페이지 번호 ex 35page = 30page
		int startpage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
		// 마지막 페이지 번호
		int endpage = maxpage;
		if (endpage > startpage + 10 - 1)
			endpage = startpage + 10 - 1;
		map.put("page", page);
		map.put("listCount", listCount);
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("category", category);
		map.put("search", search);
		
		return map;
	}
}
