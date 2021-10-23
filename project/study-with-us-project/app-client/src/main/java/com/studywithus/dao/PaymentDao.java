package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.Payment;

public interface PaymentDao {
  void insert(Payment payment) throws Exception;

  List<Payment> findAll() throws Exception;

  // [10.23 수정] mybatis 적용하면서 파라미터 값 수정함
  Payment findByNo(int studyNo, int memberNo) throws Exception;

  void update(Payment payment) throws Exception;

  // [10.23 추가]
  // void delete(Payment payment) throws Exception;
}
