package com.studywithus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.request.RequestAgent;

// 역할
// - 무료 스터디 데이터를 서버를 통해 관리한다.
//
public class NetFreeStudyDao implements FreeStudyDao {

  RequestAgent requestAgent;

  public NetFreeStudyDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Study freeStudy) throws Exception {
    requestAgent.request("freeStudy.insert", freeStudy);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 데이터 저장 실패!");
    }
  }

  @Override
  public List<Study> findAll() throws Exception {
    requestAgent.request("freeStudy.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 목록 조회 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Study.class));
  }

  @Override
  public List<Study> findByKeyword(String keyword) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request("freeStudy.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 검색 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Study.class));
  }

  @Override
  public Study findByNo(int no) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("freeStudy.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Study.class);
  }

  @Override
  public void update(Study freeStudy) throws Exception {
    requestAgent.request("freeStudy.update", freeStudy);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 변경 실패!");
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("freeStudy.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 삭제 실패!");
    }
  }
}
