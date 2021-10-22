package com.studywithus.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;

// 역할
// - 커뮤니티 데이터를 DBMS 서버를 통해 관리한다.
//
public class MybatisCommunityDao implements CommunityDao {

  SqlSession sqlSession;

  public MybatisCommunityDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Community community) throws Exception {
    sqlSession.insert("CommunityMapper.insert", community);
    sqlSession.commit();
  }

  @Override
  public List<Community> findAll() throws Exception {
    return sqlSession.selectList("CommunityMapper.findAll");
  }

  @Override
  public List<Community> findByKeyword(String keyword) throws Exception {
    return sqlSession.selectList("CommunityMapper.findByKeyword", keyword);
  }

  @Override
  public Community findByNo(int no) throws Exception {
    return sqlSession.selectOne("CommunityMapper.findByNo", no);
  }

  @Override
  public void update(Community community) throws Exception {
    sqlSession.update("CommunityMapper.update", community);
    sqlSession.commit();
  }

  @Override
  public void delete(int no) throws Exception {
    sqlSession.delete("CommunityMapper.delete", no);
    sqlSession.commit();

  }
}