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

  // 11.02 하선영
  // findAllInterest 이거 필요한건가? CmntDao 에는 없어서 질문!
  List<Study> findAllInterest(int no) throws Exception;
  int findMyInterest(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
  void insertInterest(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
  void deleteInterest(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;

  void insertLike (@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
  void deleteLike (@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
  int checkLikesByMember (@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;


}
