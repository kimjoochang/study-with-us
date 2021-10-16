package com.studywithus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;
import com.studywithus.request.RequestAgent;

public class NetMemberDao implements MemberDao {

  RequestAgent requestAgent;

  public NetMemberDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Member member) throws Exception {
    requestAgent.request("member.insert", member);
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception(requestAgent.getObject(String.class));
    //    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    requestAgent.request("member.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }

    return new ArrayList<>(requestAgent.getObjects(Member.class));
  }

  //  @Override
  //  public Member findByNo(int no) throws Exception {
  //    HashMap<String,String> params = new HashMap<>();
  //    params.put("no", String.valueOf(no));
  //
  //    requestAgent.request("member.selectOne", params);
  //
  //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //      return null;
  //    }
  //
  //    return requestAgent.getObject(Member.class);
  //  }

  @Override
  public Member findByName(String name) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);

    requestAgent.request("member.selectOneByName", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Member.class);
  }

  @Override
  public Member findByEmail(String email) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("email", email);

    requestAgent.request("member.duplicateCheck", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }
    return requestAgent.getObject(Member.class);
  }

  @Override
  public Member findMemberByEmailPassword(String email, String password) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);

    //requestAgent.request("member.selectOneByEmailPassword", params);
    requestAgent.request("member.selectOneForLogin", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }
    return requestAgent.getObject(Member.class);
  }

  @Override
  public Member findMemberByNamePhoneNumber(String name, String phoneNumber) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);
    params.put("phoneNumber", phoneNumber);

    requestAgent.request("member.selectOneForFindEmail", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }
    return requestAgent.getObject(Member.class);
  }

  @Override
  public Member findMember(String name, String email, String phoneNumber) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);
    params.put("email", email);
    params.put("phoneNumber", phoneNumber);

    requestAgent.request("member.selectOneForResetPassword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Member.class);
  }

  @Override
  public void update(Member member) throws Exception {
    requestAgent.request("member.update", member);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("변경 실패");
    }
  }

  @Override
  public void delete(String email) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("email",email);

    requestAgent.request("member.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }
}
