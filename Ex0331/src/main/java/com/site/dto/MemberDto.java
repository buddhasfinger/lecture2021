package com.site.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

	private String id,pw,name,nname,email1,email2,tel,
	address1,address2,gender,hobby;
	private Timestamp date;
}
