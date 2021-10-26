package com.studywithus.dao;

import java.util.List;

import com.studywithus.domain.Schedule;

public interface ScheduleDao {

	List<Schedule> findAll() throws Exception;
	List<Schedule> findByKeyword(String keyword) throws Exception;
	Schedule findByNo(int no) throws Exception;
	void insert(Schedule schedule) throws Exception;
	void update(Schedule schedule) throws Exception;
	void delete(int no) throws Exception;
}

