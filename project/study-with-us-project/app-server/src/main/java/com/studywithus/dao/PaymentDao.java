package com.studywithus.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.studywithus.domain.Payment;

public interface PaymentDao {
  void insert(Payment payment) throws Exception;
  List<Payment> findAll(int no) throws Exception;
  Payment findByNo(int memberNo, int studyNo) throws Exception;
  int check(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
  void update(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo, @Param("status") int statusNo) throws Exception;
  void delete(int memberNo, int studyNo) throws Exception;
}
