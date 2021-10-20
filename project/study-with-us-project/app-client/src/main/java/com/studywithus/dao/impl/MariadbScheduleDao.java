package com.studywithus.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.studywithus.dao.ScheduleDao;
import com.studywithus.domain.Schedule;

public class MariadbScheduleDao implements ScheduleDao{

	Connection con;

	public MariadbScheduleDao(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Schedule schedule) throws Exception {
	}

	@Override
	public List<Schedule> findAll() throws Exception {
		return null;
	}

	@Override
	public List<Schedule> findByKeyword(String keyword) throws Exception {
		return null;
	}

	@Override
	public Schedule findByNo(int no) throws Exception {
		return null;
	}

	@Override
	public void update(Schedule schedule) throws Exception {
	}

	@Override
	public void delete(int no) throws Exception {
	}


}
