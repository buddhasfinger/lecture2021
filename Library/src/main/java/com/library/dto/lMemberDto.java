package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class lMemberDto {
	//<!-- 4월 8일 -->
	
	private int lm_id;
	private String lm_pw;
	private String lm_name;
	private String lm_tel;
	private String lm_email1;
	private String lm_email2;
}
