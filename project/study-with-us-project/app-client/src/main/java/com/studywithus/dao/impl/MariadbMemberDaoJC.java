package com.studywithus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;

public class MariadbMemberDaoJC implements MemberDao {

  Connection con;

  public MariadbMemberDaoJC(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Member member) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into member(name,email,password,phone_number) values(?,?,password(?),?)")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      //stmt.setString(4, member.getPhoto());
      stmt.setString(4, member.getPhoneNumber());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 저장 실패!");
      }
    }

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
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no,name,email,phone_number,join_date,status from member"
            + " where email=?")) {

      stmt.setString(1, email);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }
        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPhoneNumber(rs.getString("phone_number"));
        member.setRegisteredDate(rs.getDate("join_date"));
        //        member.setLastDate(rs.getDate("last_date"));
        //        member.setStatus(rs.getInt("status"));
        //member.setUserAccessLevel(rs.getInt("access_level"));
        return member;
      }
    }
  }

  @Override
  public Member findMemberByEmailPassword(String email, String password) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no,name,email,phone_number,join_date,status from member"
            + " where email=? and password=password(?)")) {

      stmt.setString(1, email);
      stmt.setString(2, password);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }
        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPhoneNumber(rs.getString("phone_number"));
        member.setRegisteredDate(rs.getDate("join_date"));
        //        member.setLastDate(rs.getDate("last_date"));
        //        member.setStatus(rs.getInt("status"));
        //member.setUserAccessLevel(rs.getInt("access_level"));
        return member;
      }
    }
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
  public Member findByNo(int no) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(int no) throws Exception {
    // TODO Auto-generated method stub

  }
}