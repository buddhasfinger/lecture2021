package com.site.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.site.dto.BoardDto;
import com.site.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	@Autowired
	PageNumber pageNumber;
	Map<String, Object> map = new HashMap<String, Object>();
	List<BoardDto> list;
	BoardDto boardDto;
	BoardDto preDto;
	BoardDto nextDto;
	int limit;

	@Override
	public Map<String, Object> boardListAll(String listpage, String category, String search) {
		Map map = new HashMap<String, Object>();
		list = new ArrayList();
		int page = 1; // 첫페이지 초기화
		int limit = 10; // 한 페이지에 표시되는 게시글 개수
		// page 데이터가 있으면 데이터값 적용
		if (listpage != null && listpage != "") {
			page = Integer.parseInt(listpage);
		}

		int startrow = (page - 1) * limit + 1; // 시작 게시글번호 1,11,21...
		int endrow = startrow + limit - 1;// 마지막 게시글 번호 10,20,30...

		// 리스트 가져오는 메소드
		if (category == null || category.equals("")) { // 검색이 없을때
			list = boardMapper.selectBoardListAll(startrow, endrow);
		} else if (category.equals("title")) { // option: title search: 제목
			list = boardMapper.selectBoardListTitle(startrow, endrow, search);
		} else if (category.equals("content")) {
			list = boardMapper.selectBoardListContent(startrow, endrow, search);
		} else if (category.equals("all")) {
			list = boardMapper.selectBoardListSearchAll(startrow, endrow, search);
		}

		map = pageNumber.PageNumber(page, limit, category, search);
		map.put("list", list);

		return map;
	}

	@Override
	public Map<String, Object> boardContent_view(String bid, String page, String category, String search) {
		// 조회수 1 증가
		boardMapper.selectUpHit(bid);
		// content 1개 가져오기
		boardDto = boardMapper.selectBoardContent_view(bid);
		
		// 리스트 가져오는 메소드
		if (category == null || category.equals("")) { // 검색이 없을때
			//이전글 다음글을 가지고 옴
			preDto = boardMapper.selectBoard_pre(bid);
			nextDto = boardMapper.selectBoard_next(bid);
		} else if (category.equals("title")) { // option: title search: 제목
			preDto = boardMapper.selectBoard_preTitle(bid,search);
			nextDto = boardMapper.selectBoard_nextTitle(bid,search);
		} else if (category.equals("content")) {
			preDto = boardMapper.selectBoard_preContent(bid,search);
			nextDto = boardMapper.selectBoard_nextContent(bid,search);
		} else if (category.equals("all")) {
			preDto = boardMapper.selectBoard_preAll(bid,search);
			nextDto = boardMapper.selectBoard_nextAll(bid,search);
		}
		map.put("preDto", preDto);
		map.put("nextDto", nextDto);
		map.put("boardDto", boardDto);
		map.put("category", category);
		map.put("search", search);
		map.put("page", page);

		return map;
	}

	@Override
	public void BoardWrite(BoardDto boardDto, @RequestPart MultipartFile file) {
			// 원본파일이름
			String filename = file.getOriginalFilename();
			// 확장자명 가져오기
			String fileNameExtension = FilenameUtils.getExtension(filename).toLowerCase();
			if (FilenameUtils.getExtension(filename).toLowerCase() != "") {
			// 파일 저장 위치
			String fileUrl = "C:/Users/User/git/lectures/Ex0331/src/main/resources/static/upload/"; // 꼭 마지막에도 / 넣어야 그
																									// 하위파일을 인식함
			// 신규파일이름 (32자리 이름 생성.확장자명)
			String uploadFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
			File f = new File(fileUrl + uploadFileName);
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 파일이름저장
			boardDto.setFilename(uploadFileName);
		}else {
			boardDto.setFilename("");
		}

		// mapper전달
		boardMapper.insertBoardWrite(boardDto);

		return;
	}

	@Override
	public Map<String, Object> boardModify_view(String bid, String page, String category, String search) {
		// content 1개 가져오기
		boardDto = boardMapper.selectBoardContent_view(bid);
		map.put("boardDto", boardDto);
		map.put("category", category);
		map.put("search", search);
		map.put("page", page);

		return map;
	}

	@Override
	public void BoardModify(BoardDto boardDto, MultipartFile file) {
			// 원본파일이름
			String orgfilename = file.getOriginalFilename();
			System.out.println("impl:"+orgfilename);
		if (file.getSize() != 0) { //파일사이즈가 0이 아니면 
			// 파일 저장 위치
			String fileUrl = "C:/Users/User/git/lectures/Ex0331/src/main/resources/static/upload/"; // 꼭 마지막에도 / 넣어야 그
			// 신규파일이름 (32자리 이름 생성.확장자명)
			// 이름에 시간추가
			long time = System.currentTimeMillis();
			//String uploadFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
			String uploadFileName = String.format("%d_%s",time,orgfilename);
			File f = new File(fileUrl + uploadFileName);
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 파일이름저장
			boardDto.setFilename(uploadFileName);
		}else {
			//기존 파일이름을 그대로 저장시키면 됨.
			//boardDto.setFilename("");
		}

		// mapper전달
		boardMapper.updateBoardWrite(boardDto);


	}

	@Override
	public void boardReply(BoardDto boardDto, MultipartFile file) {
		// 원본파일이름
		String orgfilename = file.getOriginalFilename();
		System.out.println("impl:" + orgfilename);
		if (file.getSize() != 0) { // 파일사이즈가 0이 아니면
			// 파일 저장 위치
			String fileUrl = "C:/Users/User/git/lectures/Ex0331/src/main/resources/static/upload/"; // 꼭 마지막에도 / 넣어야 그
			// 신규파일이름 (32자리 이름 생성.확장자명)
			// 이름에 시간추가
			long time = System.currentTimeMillis();
			// String uploadFileName = RandomStringUtils.randomAlphanumeric(32) + "." +
			// fileNameExtension;
			String uploadFileName = String.format("%d_%s", time, orgfilename);
			File f = new File(fileUrl + uploadFileName);
			try {
				file.transferTo(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 파일이름저장
			boardDto.setFilename(uploadFileName);
		} else {
			// 기존 파일이름을 그대로 저장시키면 됨.
			 boardDto.setFilename("");
		}

		// mapper전달
		boardMapper.insertBoardReply(boardDto);
		boardMapper.insertBoardReplyPlus(boardDto);
		

	}

	@Override
	public void boardDelete(String bid) {
		boardMapper.deleteBoardDelete(bid);
	}

}
