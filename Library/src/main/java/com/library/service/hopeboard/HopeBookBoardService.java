package com.library.service.hopeboard;

import java.util.Map;

import com.library.dto.HopeBookBoardDto;
import com.library.dto.lMemberDto;

public interface HopeBookBoardService {
	
	
	
	
	Map<String, Object> hboardListAll(String page,String category,String search);

	Map<String, Object> boardContent_view(String hb_Seq, String page, String category, String search);

	void BoardWrite(HopeBookBoardDto boardDto);

	void ReplyWrite(String hrw_Content, String hrw_User,String hrw_Group);


}
