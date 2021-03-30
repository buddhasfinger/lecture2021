package com.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.site.dto.MemberDto;
import com.site.mapper.MemberMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	MemberMapper mapper;
	
	@Override
	public int login(MemberDto dto) {
		MemberDto memberdto = mapper.selectLoginCheck(dto);
		int result=0;
		if(memberdto!=null) {
			result= 1;
		}else {
			result=-1;
		}
		return result;
	}

	
	
}
