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
  public String findByEmail(String email) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("email", email);

    requestAgent.request("member.duplicateCheck", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }
    return email;
  }

  @Override
  public Member findMember(String name, String email, String phoneNumber) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("name", name);
    params.put("email", email);
    params.put("phoneNumber", phoneNumber);

    requestAgent.request("member.findMemberForResetPassword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Member.class);
  }

  @Override
  public void update(Member member) throws Exception {
    requestAgent.request("member.update", member);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("member.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception(requestAgent.getObject(String.class));
    }
  }
}
