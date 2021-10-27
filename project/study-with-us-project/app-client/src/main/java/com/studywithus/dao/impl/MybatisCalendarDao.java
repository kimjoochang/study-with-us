package com.studywithus.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.CalendarDao;
import com.studywithus.domain.Calendar;

public class MybatisCalendarDao implements CalendarDao{

  SqlSession sqlSession;

  public MybatisCalendarDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Calendar schedule) throws Exception {
    sqlSession.insert("CalendarMapper.insert",schedule);
    sqlSession.commit();
  }

  @Override
  public List<Calendar> findAll() throws Exception {
    return sqlSession.selectList("CalendarMapper.findAll");
  }

  @Override
  public List<Calendar> findByKeyword(String keyword) throws Exception {
    return sqlSession.selectList("CalendarMapper.findByKeyword");
  }

  @Override
  public Calendar findByNo(int no) throws Exception {
    return sqlSession.selectOne("CalendarMapper.findByNo", no);
  }

  @Override
  public void update(Calendar schedule) throws Exception {
    sqlSession.update("CalendarMapper.update", schedule);
    sqlSession.commit();
  }

  @Override
  public void delete(int no) throws Exception {
    sqlSession.delete("CalendarMapper.delete", no);
    sqlSession.commit();
  }
}
