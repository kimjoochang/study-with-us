package com.studywithus.dao.impl;

import java.sql.Connection;
import java.util.List;

import com.studywithus.dao.CalendarDao;
import com.studywithus.domain.Calendar;

public class NetScheduleDao implements CalendarDao{

	Connection con;

	public NetScheduleDao(Connection con) {
		this.con = con;
	}

	@Override
	public void insert(Calendar schedule) throws Exception {

	}

	@Override
	public List<Calendar> findAll() throws Exception {
		return null;
	}

	@Override
	public List<Calendar> findByKeyword(String keyword) throws Exception {
		return null;
	}

	@Override
	public Calendar findByNo(int no) throws Exception {
		return null;
	}

	@Override
	public void update(Calendar schedule) throws Exception {
	}

	@Override
	public void delete(int no) throws Exception {
	}

	//  private String selectType(Schedule schedule) {
	//    if (schedule.getEndDate() == null) {
	//      return "examSchedule";
	//    } else {
	//      return "jobsSchedule";
	//    }
	//  }

}