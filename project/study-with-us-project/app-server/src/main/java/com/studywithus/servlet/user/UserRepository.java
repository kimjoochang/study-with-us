package com.studywithus.servlet.user;

public interface UserRepository {
  // 아이디 중복 체크
  public int emailCheck(String email);
}
