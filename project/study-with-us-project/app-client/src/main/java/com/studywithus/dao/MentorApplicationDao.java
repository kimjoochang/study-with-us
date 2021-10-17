package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.MentorApplicationForm;

public interface MentorApplicationDao {

  void insert(MentorApplicationForm mentorApplication) throws Exception;
  List<MentorApplicationForm> findAll() throws Exception;
  //List<Member> findByName(String name) throws Exception;
  MentorApplicationForm findByEmail(String email) throws Exception;
  void update(MentorApplicationForm mentorApplication) throws Exception;
  void delete(String mentorApplicantEmail) throws Exception;
}
