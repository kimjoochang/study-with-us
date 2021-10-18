package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.Member;

public interface MemberDao {
  void insert(Member member) throws Exception;
  List<Member> findAll() throws Exception;
  Member findByNo(int no) throws Exception;
  Member findByName(String name) throws Exception;
  Member findByEmail(String email) throws Exception; // 회원가입 시 아이디 중복검사
  Member findMemberByEmailPassword(String email, String password) throws Exception; // 로그인
  Member findMemberByNamePhoneNumber(String name, String phoneNumber) throws Exception; // 아이디 찾기
  Member findMember(String name, String email, String phoneNumber) throws Exception; // 비밀번호 재설정
  void update(Member member) throws Exception;
  // void delete(String email) throws Exception;
  void delete(int no) throws Exception;
}
