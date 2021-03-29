package com.site.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.site.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Override
	public HashMap<String, Object> boardList(String page,String category,String search) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		int limit = 10;

		
		
		//list, list가져오기 - db연결
		int listCount = 30; //db연결
		int nowPage = Integer.parseInt(page);//현재페이지수 
		//최대페이지수
		int maxpage= (int)((double)listCount/limit+0.95);
		//매 선택된 페이지 마다 제일 앞에 시작할 첫 페이지 번호 ex 35page = 30page
		int startpage = ((int)((double)nowPage/10+0.9)-1) * 10+1;
		//마지막 페이지 번호
		int endpage= maxpage;
		if(endpage>startpage+10-1) endpage = startpage+10-1;
		
		//map.put("list", list); //list - bId,bName,bTitle,bContent,bDate...
		//결과 찍을 땐 ${map.list.bId}
		map.put("listCount", listCount);
		map.put("nowPage", nowPage);
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		
		return map;
	}

	
	
	@Override
	public HashMap<String, Object> boardFileWrite(BoardDto boardDto, MultipartFile file) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//파일의 이름
				String fileName= file.getOriginalFilename();
				//확장자명 - 1.jpg 
				String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
				String fileUrl = "C:/workspace/Ex0329_02/src/main/resources/static/upload/";
				// http://localhost:8080/upload/1.png
				
				//중복이름 방지를 위한 이름 생성 - 랜덤이름생성 : 알파벳,숫자로 이뤄진 30자리 이름생성
				String uploadFileName = RandomStringUtils.randomAlphanumeric(30)+"."+fileNameExtension;
						//1.jpg -> STRRcew234234.jpg
				
				File f = new File(fileUrl + uploadFileName);
				try {
					file.transferTo(f);
				} catch (Exception e) {
					e.printStackTrace();
				} //파일 저장메소드
				
				
				map.put("BoardDto",boardDto);
				map.put("fileName",fileName);
				map.put("fileNameExtension",fileNameExtension);
				map.put("fileUrl",fileUrl);
				map.put("uploadFileName",uploadFileName);
		
				return map;
	}

}//class
