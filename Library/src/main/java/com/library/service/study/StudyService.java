package com.library.service.study;

import java.util.List;

import com.library.dto.RoomBookingDto;

public interface StudyService {

	int[] dateCheck(int sr_id,String rb_date);

	void roomBooking(RoomBookingDto roombookingDto);
	//void roomBooking(int sr_id, String rb_date, int rb_time);

}
