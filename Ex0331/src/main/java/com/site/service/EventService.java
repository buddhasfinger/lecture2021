package com.site.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.site.dto.CommentDto;
import com.site.mapper.EventMapper;


public interface EventService {


	CommentDto commentWrite_check(CommentDto commentDto);

}
