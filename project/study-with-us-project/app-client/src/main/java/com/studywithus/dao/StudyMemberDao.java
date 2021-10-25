package com.studywithus.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.studywithus.domain.Study;

public interface StudyMemberDao {
  List<Study> findAllStudy(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
  List<Study> findAllMember(@Param("studyNo") int memberNo, @Param("myStatus") int statusNo) throws Exception;
  void insert(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo, @Param("myStatus") int statusNo) throws Exception;
  void update(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo, @Param("myStatus") int statusNo) throws Exception;
  void delete(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
}
