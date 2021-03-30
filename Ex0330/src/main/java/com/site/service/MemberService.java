package com.site.service;

import java.util.ArrayList;
import java.util.Map;

import com.site.dto.MemberDto;

public interface MemberService {
	
	int loginCheck(MemberDto memberDto);
	
	Map<String,Object> MemberListAll();
	
	
}
