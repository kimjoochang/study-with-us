package com.studywithus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.dao.ChargeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.request.RequestAgent;

public class NetChargeStudyDao implements ChargeStudyDao {

  RequestAgent requestAgent;

  public NetChargeStudyDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Study chargeStudy) throws Exception {
    requestAgent.request("chargeStudy.insert", chargeStudy);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유료 스터디 데이터 저장을 실패하였습니다.");
    }
  }

  @Override
  public List<Study> findAll() throws Exception {
    requestAgent.request("chargeStudy.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유료 스터디 목록 조회를 실패하였습니다.");
    }

    return new ArrayList<>(requestAgent.getObjects(Study.class));
  }

  @Override
  public List<Study> findByKeyword(String keyword) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request("chargeStudy.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유료 스터디 검색을 실패하였습니다.");
    }

    return new ArrayList<>(requestAgent.getObjects(Study.class));
  }

  @Override
  public Study findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("chargeStudy.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Study.class);
  }

  @Override
  public void update(Study chargeStudy) throws Exception {
    requestAgent.request("chargeStudy.update", chargeStudy);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유료 스터디 변경을 실패하였습니다.");
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("chargeStudy.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유료 스터디 삭제를 실패하였습니다.");
    }
  }

}
