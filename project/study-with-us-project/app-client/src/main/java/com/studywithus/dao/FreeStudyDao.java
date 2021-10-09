package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.Study;

// 역할
// - 무료 스터디 데이터를 처리하는 객체 사용법을 정의한다.
//
public interface FreeStudyDao {
  void insert(Study freeStudy) throws Exception;

  List<Study> findAll() throws Exception;

  List<Study> findByKeyword(String keyword) throws Exception;

  Study findByNo(int no) throws Exception;

  void update(Study freeStudy) throws Exception;

  void delete(int no) throws Exception;
}
