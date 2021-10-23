package com.studywithus.dao;

import java.util.List;
import java.util.Map;
import com.studywithus.domain.Study;

public interface StudyDao {
  List<Study> findAll() throws Exception;
  List<Study> findByKeyword(String keyword) throws Exception;
  Study findByNo(int no) throws Exception;
  void insert(Study study) throws Exception;
  void insertInterest(Map<String,Object> params) throws Exception;
  void update(Study chargeStudy) throws Exception;
  List<Study> findAllRegisterStudy(int no) throws Exception;
  List<Study> findAllParticipateStudy(int no) throws Exception;
  Study findByNoRegisterStudy(Map<String,Object> params, String type) throws Exception;
  Study findByNoParticipateStudy(Map<String,Object> params, String type) throws Exception;
  List<Study> findAllInterest(int no) throws Exception;
  Study findByNoInterest(Map<String,Object> params) throws Exception;
  void delete(int no) throws Exception;
}
