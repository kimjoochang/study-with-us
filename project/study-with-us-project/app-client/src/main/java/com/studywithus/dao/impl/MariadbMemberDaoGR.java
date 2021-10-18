package com.studywithus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;

public class MariadbMemberDaoGR implements MemberDao {

  Connection con;

  public MariadbMemberDaoGR(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Member member) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into member(name,email,password,phone_number) values(?,?,password(?),?)")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getPhoneNumber());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 저장을 실패하였습니다.");
      }
    }
    // requestAgent.request("member.insert", member);
    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // throw new Exception(requestAgent.getObject(String.class));
    // }
  }

  @Override
  public List<Member> findAll() throws Exception {
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select member_no,name,email,phone_number,join_date from member order by name asc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_no"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setPhoneNumber(rs.getString("phone_number"));
        member.setRegisteredDate(rs.getDate("join_date"));

        list.add(member);
      }

      return list;
    }
    // requestAgent.request("member.selectList", null);
    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // throw new Exception(requestAgent.getObject(String.class));
    // }
    //
    // return new ArrayList<>(requestAgent.getObjects(Member.class));
  }

  @Override
  public Member findByNo(int no) throws Exception {
    try (
        PreparedStatement stmt = con.prepareStatement(
            "select member_no,name,email,phone_number,join_date from member where member_no=" + no);
        ResultSet rs = stmt.executeQuery()) {

      if (!rs.next()) {
        return null;
      }

      Member member = new Member();
      member.setNo(rs.getInt("member_no"));
      member.setName(rs.getString("name"));
      member.setEmail(rs.getString("email"));
      member.setPhoneNumber(rs.getString("phone_number"));
      member.setRegisteredDate(rs.getDate("join_date"));

      return member;
    }
    // HashMap<String,String> params = new HashMap<>();
    // params.put("no", String.valueOf(no));
    //
    // requestAgent.request("member.selectOne", params);
    //
    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // return null;
    // }
    //
    // return requestAgent.getObject(Member.class);
  }

  @Override
  public Member findByName(String name) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no,name,email,phone_number,join_date from member" + " where name=?")) {

      stmt.setString(1, name);

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
        return member;
      }
    }
    // HashMap<String, String> params = new HashMap<>();
    // params.put("name", name);
    //
    // requestAgent.request("member.selectOneByName", params);
    //
    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // return null;
    // }
    //
    // return requestAgent.getObject(Member.class);
  }

  @Override
  public Member findByEmail(String email) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select member_no,name,email,phone_number,join_date from member" + " where email=?")) {

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
        return member;
      }
    }
    // HashMap<String, String> params = new HashMap<>();
    // params.put("email", email);
    //
    // requestAgent.request("member.duplicateCheck", params);
    //
    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // return null;
    // }
    // return requestAgent.getObject(Member.class);
  }

  @Override
  public Member findMemberByEmailPassword(String email, String password) throws Exception {
    try (PreparedStatement stmt =
        con.prepareStatement("select member_no,name,email,phone_number,join_date from member"
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
        return member;
      }
    }
    // HashMap<String, String> params = new HashMap<>();
    // params.put("email", email);
    // params.put("password", password);
    //
    // // requestAgent.request("member.selectOneByEmailPassword", params);
    // requestAgent.request("member.selectOneForLogin", params);
    //
    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // return null;
    // }
    // return requestAgent.getObject(Member.class);
  }

  @Override
  public Member findMemberByNamePhoneNumber(String name, String phoneNumber) throws Exception {
    // HashMap<String, String> params = new HashMap<>();
    // params.put("name", name);
    // params.put("phoneNumber", phoneNumber);
    //
    // requestAgent.request("member.selectOneForFindEmail", params);
    //
    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // return null;
    // }
    // return requestAgent.getObject(Member.class);
  }

  @Override
  public Member findMember(String name, String email, String phoneNumber) throws Exception {
    // HashMap<String, String> params = new HashMap<>();
    // params.put("name", name);
    // params.put("email", email);
    // params.put("phoneNumber", phoneNumber);
    //
    // requestAgent.request("member.selectOneForResetPassword", params);
    //
    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // return null;
    // }
    //
    // return requestAgent.getObject(Member.class);
  }

  @Override
  public void update(Member member) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement("update member set"
        + " name=?,email=?,password=password(?),phone_number=?" + " where member_no=?")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getPhoneNumber());
      stmt.setInt(5, member.getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 변경 실패!");
      }
    }
    // requestAgent.request("member.update", member);
    //
    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // throw new Exception("변경 실패");
    // }
  }

  @Override
  public void delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement("delete from member where member_no=?")) {

      stmt.setInt(1, no);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 삭제를 실패하였습니다.");
      }
    }
    // HashMap<String, String> params = new HashMap<>();
    // params.put("email", email);
    //
    // requestAgent.request("member.delete", params);
    //
    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // throw new Exception(requestAgent.getObject(String.class));
    // }
  }
}
