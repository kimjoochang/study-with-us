package com.studywithus.servlet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserRepository userRepository;

  // 아이디 중복 체크
  @Override
  public int emailCheck(String email) {
    int cnt = userRepository.emailCheck(email);
    return cnt;
  }
}