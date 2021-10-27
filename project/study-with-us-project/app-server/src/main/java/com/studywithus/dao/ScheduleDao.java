package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.Calendar;

public interface ScheduleDao {

  void insert(Calendar schedule) throws Exception;
  List<Calendar> findAll() throws Exception;
  List<Calendar> findByKeyword(String keyword) throws Exception;
  Calendar findByNo(int no) throws Exception;
  void update(Calendar schedule) throws Exception;
  void delete(int no) throws Exception;
}

