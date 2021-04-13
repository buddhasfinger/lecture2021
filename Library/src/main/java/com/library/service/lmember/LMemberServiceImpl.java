package com.library.service.lmember;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.lMemberDto;
import com.library.mapper.LMember_Mapper;


@Service
public class LMemberServiceImpl implements LMemberService {
	
	@Autowired
	LMember_Mapper lmember_mapper; 

	
	@Override
	public Map logincheck(lMemberDto dto) {
		Map map = new HashMap<String, Object>();
		lMemberDto memberDto =lmember_mapper.selectloginCheck(dto);
		
		int loginCheck = -1;
		if(memberDto != null) {
				loginCheck= 1;  //dto에 데이터가 있으면 성공!
		}
		
		map.put("memberDto",memberDto);
		map.put("loginCheck",loginCheck);
		
		return map;
	}
}
