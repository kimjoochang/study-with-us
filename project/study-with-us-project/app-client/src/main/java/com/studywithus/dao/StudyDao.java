package com.studywithus.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.studywithus.domain.Study;

public interface StudyDao {

  List<Study> findAll() throws Exception;
  List<Study> findByKeyword(String keyword) throws Exception;
  Study findByNo(int no) throws Exception;
  void insert(Study study) throws Exception;
  void update(Study chargeStudy) throws Exception;

  void insertInterest(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
  List<Study> findAllInterest(int no) throws Exception;
  Study findByNoInterest(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;

  List<Study> findAllRegisterStudy(int no) throws Exception;
  Study findByNoRegisterStudy(@Param("writerNo") int writerNo, @Param("studyNo") int studyNo) throws Exception;

  List<Study> findAllParticipateStudy(@Param("memberNo") int memberNo, @Param("myStatus") int status) throws Exception;
  Study findByNoParticipateStudy(@Param("memberNo") int writerNo, @Param("studyNo") int studyNo, @Param("myStatus") int status) throws Exception;

  List<Study> findAllApplyStudy(int memberNo) throws Exception;
  Study findByNoApplyStudy(@Param("memberNo") int writerNo, @Param("studyNo") int studyNo) throws Exception;

  // 0 = 무료 신청, 1 = 무료 참여자, 2 = 유료 참여자(결제한 사람)
  void insertStudyMember(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo, @Param("myStatus") int status) throws Exception;
  void updateStatus(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo, @Param("myStatus") int status) throws Exception;


  void delete(int no) throws Exception;
  void deleteStudyMember(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
}
