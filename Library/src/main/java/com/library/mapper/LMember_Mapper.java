package com.library.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.library.dto.lMemberDto;

@Mapper
public interface LMember_Mapper {

	lMemberDto selectloginCheck(lMemberDto dto);

}
