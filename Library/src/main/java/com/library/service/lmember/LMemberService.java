package com.library.service.lmember;

import java.util.Map;

import com.library.dto.lMemberDto;

public interface LMemberService {

	Map<String, Object> logincheck(lMemberDto dto);

}
