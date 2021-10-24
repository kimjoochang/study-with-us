package com.studywithus.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.ReviewDao;
import com.studywithus.domain.Review;

public class MybatisReviewDao implements ReviewDao {

  SqlSession sqlSession;

  public MybatisReviewDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Review review) throws Exception {
    sqlSession.insert("ReviewMapper.insert", review);
    sqlSession.commit();
  }

  @Override
  public List<Review> findAll() throws Exception {
    return sqlSession.selectList("ReviewMapper.findAll");
  }
}