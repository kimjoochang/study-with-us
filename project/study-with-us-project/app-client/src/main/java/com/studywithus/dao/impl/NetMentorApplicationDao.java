package com.studywithus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.request.RequestAgent;

public class NetMentorApplicationDao implements MentorApplicationDao {

  RequestAgent requestAgent;

  public NetMentorApplicationDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(MentorApplicationForm mentorApplication) throws Exception {
    requestAgent.request("mentorApplication.insert", mentorApplication);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유료 스터디 데이터 저장을 실패하였습니다.");
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    requestAgent.request("mentorApplication.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유료 스터디 목록 조회를 실패하였습니다.");
    }

    return new ArrayList<>(requestAgent.getObjects(Member.class));
  }

  @Override
  public List<Member> findByName(String name) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);
    requestAgent.request("mentorApplication.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유료 스터디 검색을 실패하였습니다.");
    }

    return new ArrayList<>(requestAgent.getObjects(Member.class));
  }

  @Override
  public List<Member> findByEmail(String email) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("email", email);

    requestAgent.request("mentorApplication.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return new ArrayList<>(requestAgent.getObjects(Member.class));
  }

  @Override
  public void update(MentorApplicationForm mentorApplication) throws Exception {
    requestAgent.request("mentorApplication.update", mentorApplication);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유료 스터디 변경을 실패하였습니다.");
    }
  }

  @Override
  public void delete(String mentorApplicantEmail) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("email", mentorApplicantEmail);

    requestAgent.request("mentorApplication.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유료 스터디 삭제를 실패하였습니다.");
    }
  }

}
