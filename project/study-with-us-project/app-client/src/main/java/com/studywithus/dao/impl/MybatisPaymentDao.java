package com.studywithus.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.PaymentDao;
import com.studywithus.domain.Payment;

public class MybatisPaymentDao implements PaymentDao {

  SqlSession sqlSession;

  public MybatisPaymentDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Payment payment) throws Exception {
    sqlSession.insert("PaymentMapper.insert", payment);
    sqlSession.commit();
  }

  @Override
  public List<Payment> findAll() throws Exception {
    return sqlSession.selectList("PaymentMapper.findAll");
  }

  @Override
  public Payment findByNo(int studyNo, int memberNo) throws Exception {
    HashMap<Object,Object> params = new HashMap<>();
    params.put("studyNo", String.valueOf(studyNo));
    params.put("memberNo", String.valueOf(memberNo));

    return sqlSession.selectOne("PaymentMapper.findByNo", params);
  }

  @Override
  public void update(Payment payment) throws Exception {
    sqlSession.update("PaymentMapper.update", payment);
    sqlSession.commit();
  }

}
