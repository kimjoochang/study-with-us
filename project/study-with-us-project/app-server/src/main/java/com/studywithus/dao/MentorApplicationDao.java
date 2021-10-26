package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.MentorApplication;

public interface MentorApplicationDao {

  void insert(MentorApplication mentorApplication) throws Exception;
  List<MentorApplication> findAll() throws Exception;
  MentorApplication findByNo(int no) throws Exception;
  void update(MentorApplication mentorApplication) throws Exception;
  //void delete(String mentorApplicantEmail) throws Exception;
}
