package com.studywithus.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.studywithus.domain.Member;

public interface MemberDao {
  List<Member> findAll() throws Exception;
  Member findByNo(int no) throws Exception;
  Member findByEmail(String email) throws Exception; // 회원가입 시 아이디 중복검사
  Member findMemberByNamePhoneNumber(@Param("name") String name, @Param("phoneNumber") String phoneNumber) throws Exception; // 아이디 찾기
  Member findMember(@Param("name") String name, @Param("email") String email, @Param("phoneNumber") String phoneNumber) throws Exception; // 비밀번호 재설정
  Member findMemberByEmailPassword(@Param("email") String email, @Param("password") String password) throws Exception; // 로그인

  //11.15추가
  Member emailCheck(String email) throws Exception; 

  void insert(Member member) throws Exception;
  void update(Member member) throws Exception;
  void updatePassword(Member member) throws Exception;
  void delete(int no) throws Exception;

}
