package com.studywithus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.MentorApplication;
import com.studywithus.request.RequestAgent;

public class NetMentorApplicationDao implements MentorApplicationDao {

  RequestAgent requestAgent;

  public NetMentorApplicationDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(MentorApplication mentorApplication) throws Exception {
    requestAgent.request("mentorApplication.insert", mentorApplication);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("멘토 신청 데이터 저장을 실패하였습니다.");
    }
  }

  @Override
  public List<MentorApplication> findAll() throws Exception {
    requestAgent.request("mentorApplication.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("멘토 신청 목록 조회를 실패하였습니다.");
    }

    return new ArrayList<>(requestAgent.getObjects(MentorApplication.class));
  }
  //
  //  @Override
  //  public List<Member> findByName(String name) throws Exception {
  //    HashMap<String,String> params = new HashMap<>();
  //    params.put("name", name);
  //    requestAgent.request("mentorApplication.selectListByKeyword", params);
  //
  //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //      throw new Exception("멘토 신청 검색을 실패하였습니다.");
  //    }
  //
  //    return new ArrayList<>(requestAgent.getObjects(Member.class));
  //  }

  @Override
  public MentorApplication findByEmail(String email) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("email", email);

    requestAgent.request("mentorApplication.selectOneByEmail", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(MentorApplication.class);
  }
  //
  @Override
  public void update(MentorApplication mentorApplication) throws Exception {
    requestAgent.request("mentorApplication.update", mentorApplication);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("멘토 신청 변경을 실패하였습니다.");
    }
  }

  @Override
  public void delete(String mentorApplicantEmail) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("email", mentorApplicantEmail);

    requestAgent.request("mentorApplication.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("멘토 신청 삭제를 실패하였습니다.");
    }
  }

}
