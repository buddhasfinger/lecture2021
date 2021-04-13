package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HopeBookBoardDto {

	private int hb_Seq,hb_Hit;
	private String hb_User,hb_Title,hb_Content,hb_Date;
	
}
