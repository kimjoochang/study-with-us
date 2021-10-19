package com.studywithus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;

public class MariadbMemberDaoJJ implements MemberDao {

  Connection con;

  public MariadbMemberDaoJJ(Connection con) {
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
        throw new Exception("회원 데이터 저장 실패!");
      }
    }

  }


  @Override
  public List<Member> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select name,email,password,phone_number from pms_member order by name asc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member member = new Member();

        stmt.setString(1, member.getName());
        stmt.setString(2, member.getEmail());
        stmt.setString(3, member.getPassword());
        stmt.setString(4, member.getPhoneNumber());


        list.add(member);
      }

      return list;
    }
  }
  // MemberDao에 있는데 현재 클래스에서 구현 안 한 메서드
  // - 어차피 이제 회원번호 쓰니까 어느정도 정리할듯?

  @Override
  public Member findByEmail(String email) throws Exception {
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

  //


  @Override
  public Member findByNo(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select name,email,password,phone_number from pms_member where member_no=" + no);
        ResultSet rs = stmt.executeQuery()) {

      if (!rs.next()) {
        return null;
      }

      Member member = new Member();

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getPhoneNumber());

      return member;
    }
  }

  @Override
  public Member findByName(String name) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select name,email,password,phone_number from pms_member"
            + " where name=?")) {

      stmt.setString(1, name);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }
        Member member = new Member();

        stmt.setString(1, member.getName());
        stmt.setString(2, member.getEmail());
        stmt.setString(3, member.getPassword());
        stmt.setString(4, member.getPhoneNumber());

        return member;
      }
    }
  }

  @Override
  public Member findMemberByEmailPassword(String email, String password) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "select name,email,password,phone_number from pms_member"
            + " where email=? and password=password(?)")) {

      stmt.setString(1, email);
      stmt.setString(2, password);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }
        Member member = new Member();

        stmt.setString(1, member.getName());
        stmt.setString(2, member.getEmail());
        stmt.setString(3, member.getPassword());
        stmt.setString(4, member.getPhoneNumber());

        return member;
      }
    }
  }


  @Override
  public void update(Member member) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update pms_member set"
            + " name=?,email=?,password=password(?),photo=?,tel=?"
            + " where member_no=?")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getPhoneNumber());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 변경 실패!");
      }
    }
  }

  @Override
  public void delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from pms_member where member_no=?")) {

      stmt.setInt(1, no);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 삭제 실패!");
      }
    }
  }


}