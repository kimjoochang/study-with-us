package com.studywithus.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.studywithus.domain.Study;

public interface StudyDao {
  List<Study> findAll(@Param("low") int low, @Param("high") int high) throws Exception;
  List<Study> findByKeyword(String keyword) throws Exception;
  Study findByNo(int no) throws Exception;
  void insert(Study study) throws Exception;
  void update(Study study) throws Exception;
  void updateCount(int no) throws Exception;
  void delete(int no) throws Exception;

  // 나의 관심목록 스터디
  List<Study> findAllInterest(@Param("memberNo") int memberNo, @Param("low") int low, @Param("high") int high) throws Exception;
  int findMyInterest(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;

  // 관심목록 추가
  void insertInterest(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
  void deleteInterest(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;

  int checkLikesByMember (@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;


}
