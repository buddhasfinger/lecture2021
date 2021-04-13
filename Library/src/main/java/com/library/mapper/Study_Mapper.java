package com.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.library.dto.RoomBookingDto;

@Mapper
public interface Study_Mapper {

	//ArrayList occupiedRoomList(int sr_id,String rb_date);
	List occupiedRoomList(@Param("sr_id") int sr_id, @Param("rb_date") String rb_date);

	//void roomBooking(int sr_id, String rb_date, int rb_time);
	void roomBooking(RoomBookingDto roombookingDto);





}
