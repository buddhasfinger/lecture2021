package com.site.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.site.dto.BoardDto;
import com.site.dto.MemberDto;

@Mapper
public interface Board_Mapper {
	
	//xml선언 : namespace:Board_Mapper , id: selectBoardList
	ArrayList<BoardDto> selectBoardList();
	BoardDto selectBoardContentView(String bId);
	
	MemberDto selectloginCheck(MemberDto dto);
	
}
