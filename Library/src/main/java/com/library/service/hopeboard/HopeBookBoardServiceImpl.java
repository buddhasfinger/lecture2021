package com.library.service.hopeboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.HopeBookBoardDto;
import com.library.dto.lMemberDto;
import com.library.mapper.HopeBookBoard_Mapper;

@Service
public class HopeBookBoardServiceImpl implements HopeBookBoardService {

	@Autowired
	HopeBookBoard_Mapper board_mapper;
	@Autowired
	HopeBookPageNumber pageNumber;
	Map<String, Object> map = new HashMap<String, Object>();
	List<HopeBookBoardDto> list;
	HopeBookBoardDto boardDto;
	HopeBookBoardDto preDto;
	HopeBookBoardDto nextDto;
	int limit;
	
	

	//희망도서신청 게시판 리스트All
	@Override
	public Map<String, Object> hboardListAll(String listpage, String category, String search) {
		Map map = new HashMap<String, Object>();
		list = new ArrayList();
		int page = 1; // 첫페이지 초기화
		int limit = 5; // 한 페이지에 표시되는 게시글 개수
		// page 데이터가 있으면 데이터값 적용
		if (listpage != null && listpage != "") {
			page = Integer.parseInt(listpage);
		}

		int startrow = (page - 1) * limit + 1; // 시작 게시글번호 1,11,21...
		int endrow = startrow + limit - 1;// 마지막 게시글 번호 10,20,30...

		// 리스트 가져오는 메소드
		if (category == null || category.equals("")) { // 검색이 없을때
			list = board_mapper.selectBoardListAll(startrow, endrow);
		} else if (category.equals("title")) { // option: title search: 제목
			list = board_mapper.selectBoardListTitle(startrow, endrow, search);
		} else if (category.equals("content")) {
			list = board_mapper.selectBoardListContent(startrow, endrow, search);
		} else if (category.equals("all")) {
			list = board_mapper.selectBoardListSearchAll(startrow, endrow, search);
		}

		map = pageNumber.PageNumber(page, limit, category, search);
		map.put("list", list);

		return map;
	}
	
	//희망도서신청 글내용 보기
	@Override
	public Map<String, Object> boardContent_view(String hb_Seq, String page, String category, String search) {
		// content 1개 가져오기
		boardDto = board_mapper.selectBoardContent_view(hb_Seq);
		// 조회수 1 증가
		board_mapper.selectUpHit(hb_Seq);
		
		// 리스트 가져오는 메소드
		if (category == null || category.equals("")) { // 검색이 없을때
			//이전글 다음글을 가지고 옴
			preDto = board_mapper.selectBoard_pre(hb_Seq);
			nextDto = board_mapper.selectBoard_next(hb_Seq);
		} else if (category.equals("title")) { // option: title search: 제목
			preDto = board_mapper.selectBoard_preTitle(hb_Seq,search);
			nextDto = board_mapper.selectBoard_nextTitle(hb_Seq,search);
		} else if (category.equals("content")) {
			preDto = board_mapper.selectBoard_preContent(hb_Seq,search);
			nextDto = board_mapper.selectBoard_nextContent(hb_Seq,search);
		} else if (category.equals("all")) {
			preDto = board_mapper.selectBoard_preAll(hb_Seq,search);
			nextDto = board_mapper.selectBoard_nextAll(hb_Seq,search);
		}
		map.put("preDto", preDto);
		map.put("nextDto", nextDto);
		map.put("boardDto", boardDto);
		map.put("category", category);
		map.put("search", search);
		map.put("page", page);

		return map;
	}
	
	//희망도서신청 글쓰기
	@Override
	public void BoardWrite(HopeBookBoardDto boardDto) {
		board_mapper.insertBoardWrite(boardDto);
		
	}
	
	//희망도서신청 댓글달기
	@Override
	public void ReplyWrite(String hrw_Content, String hrw_User,String hrw_Group) {
		board_mapper.insertReplyWrite(hrw_Content,hrw_User,hrw_Group);
	}

	
	
}
