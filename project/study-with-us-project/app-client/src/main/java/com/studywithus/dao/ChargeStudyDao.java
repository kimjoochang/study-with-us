package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.Study;

public interface ChargeStudyDao {

  void insert(Study chargeStudy) throws Exception;
  List<Study> findAll() throws Exception;
  List<Study> findByKeyword(String keyword) throws Exception;
  Study findByNo(int no) throws Exception;
  void update(Study chargeStudy) throws Exception;
  void delete(int no) throws Exception;
}
