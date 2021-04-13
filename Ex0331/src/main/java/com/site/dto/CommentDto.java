package com.site.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

	private int commentNo, bid; //boardDto에서 가져온 bid
	private String id,commentPw, commentContent;
	private Timestamp commentDate;
}
