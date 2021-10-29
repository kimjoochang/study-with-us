package com.studywithus.dao;

import java.util.List;

import com.studywithus.domain.Calendar;

public interface CalendarDao {

	List<Calendar> findAll() throws Exception;
	List<Calendar> findByKeyword(String keyword) throws Exception;
	Calendar findByNo(int no) throws Exception;
	void insert(Calendar schedule) throws Exception;
	void update(Calendar schedule) throws Exception;
	void delete(int no) throws Exception;
}

