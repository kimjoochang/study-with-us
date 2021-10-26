package com.studywithus.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.studywithus.domain.Study;

public interface StudyDao {
  List<Study> findAll() throws Exception;
  List<Study> findByKeyword(String keyword) throws Exception;
  Study findByNo(int no) throws Exception;
  void insert(Study study) throws Exception;
  void insertInterest(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
  void update(Study study) throws Exception;
  List<Study> findAllInterest(int no) throws Exception;
  int findMyInterest(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
  void delete(int no) throws Exception;
  void deleteInterest(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
}
