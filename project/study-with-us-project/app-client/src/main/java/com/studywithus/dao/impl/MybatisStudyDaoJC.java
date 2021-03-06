package com.studywithus.dao.impl;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;

// 역할
// - 스터디 데이터를 서버를 통해 관리한다.
//
public class MybatisStudyDaoJC implements StudyDao {

  SqlSession sqlSession;

  public MybatisStudyDaoJC(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Study study) throws Exception {
    sqlSession.insert("StudyMapper.insert", study);
    sqlSession.commit();
  }

  @Override
  public List<Study> findAll() throws Exception {
    return sqlSession.selectList("StudyMapper.findAll");
  }

  @Override
  public List<Study> findByKeyword(String keyword) throws Exception {
    return sqlSession.selectList("StudyMapper.findByKeyword");
  }

  @Override
  public Study findByNo(int no) throws Exception {
    return sqlSession.selectOne("StudyMapper.findByNo",no);
  }

  @Override
  public void update(Study study) throws Exception {
    sqlSession.update("StudyMapper.update", study);
    sqlSession.commit();
  }

  @Override
  public void delete(int no) throws Exception {
    sqlSession.delete("StudyMapper.deleteInterest", no);
    sqlSession.delete("StudyMapper.deleteMentee", no);
    sqlSession.delete("StudyMapper.deleteReview", no);
    sqlSession.delete("StudyMapper.delete", no);
    sqlSession.commit();
  }

  @Override
  public List<Study> findAllMyStudy(int no, String type) throws Exception {
    return sqlSession.selectList("StudyMapper.findAll" + type + "Study", no);
  }

  @Override
  public Study findByNoMyStudy(Map<String,Object> params, String type) throws Exception {
    return sqlSession.selectOne("StudyMapper.findByNo" + type + "Study",params);
  }

  @Override
  public List<Study> findAllInterest(int no) throws Exception {
    return sqlSession.selectList("StudyMapper.findAllInterest",no);
  }

  @Override
  public Study findByNoInterest(Map<String, Object> params) throws Exception {
    return sqlSession.selectOne("StudyMapper.findByNoInterest",params);
  }

  @Override
  public void insertInterest(Map<String,Object> params) throws Exception {
    sqlSession.insert("StudyMapper.insertInterest", params);
    sqlSession.commit();
  }
}
