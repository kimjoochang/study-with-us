package com.studywithus.dao.impl;

import java.sql.Connection;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Study;

// 역할
// - 스터디 데이터를 서버를 통해 관리한다.
//
public class MybatisStudyDao implements FreeStudyDao {

  Connection con;
  SqlSession sqlSession;

  public MybatisStudyDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Study freeStudy) throws Exception {
    sqlSession.insert("FreeStudyMapper.insert", freeStudy);
    sqlSession.commit();
  }

  @Override
  public List<Study> findAll() throws Exception {
    return sqlSession.selectList("FreeStudyMapper.findAll");
  }

  @Override
  public Study findByKeyword(String keyword) throws Exception {
    List<Study> list = sqlSession.selectList("FreeStudyMapper.findByKeyword", keyword);
    if (list.size() > 0) {
      return list.get(0);
    } else {
      return null;
    }
  }

  @Override
  public Study findByNo(int no) throws Exception {
    return sqlSession.selectOne("FreeStudyMapper.findByNo", no);
  }

  @Override
  public void update(Study freeStudy) throws Exception {
    sqlSession.update("FreeStudyMapper.update", freeStudy);
    sqlSession.commit();
  }

  @Override
  public void delete(int no) throws Exception {
    sqlSession.delete("FreeStudyMapper.delete", no);
    sqlSession.commit();
  }
}
