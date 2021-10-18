package com.studywithus.dao.impl;

import java.sql.Connection;
import java.util.List;
import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;

public class MariadbMemberDao implements MemberDao {

  Connection con;

  public MariadbMemberDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Member member) throws Exception {

  }

  @Override
  public List<Member> findAll() throws Exception {
    return null;
  }

  @Override
  public Member findByName(String name) throws Exception {
    return null;
  }

  @Override
  public Member findByEmail(String email) throws Exception {
    return null;
  }

  @Override
  public Member findMemberByEmailPassword(String email, String password) throws Exception {
    return null;
  }

  @Override
  public Member findMemberByNamePhoneNumber(String name, String phoneNumber) throws Exception {
    return null;
  }

  @Override
  public Member findMember(String name, String email, String phoneNumber) throws Exception {
    return null;
  }

  @Override
  public void update(Member member) throws Exception {
  }

  @Override
  public void delete(String email) throws Exception {
  }
}