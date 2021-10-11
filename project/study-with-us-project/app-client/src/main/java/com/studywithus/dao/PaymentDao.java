package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.Payment;

public interface PaymentDao {
  void insert(Payment payment) throws Exception;
  List<Payment> findAll() throws Exception;
  Payment findByNo(int no) throws Exception;
  void update(Payment payment) throws Exception;
}
