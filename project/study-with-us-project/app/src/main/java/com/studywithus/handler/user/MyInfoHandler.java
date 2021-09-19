package com.studywithus.handler.user;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;

public class MyInfoHandler implements Command {

  public MyInfoHandler(List<Member> memberList) {
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[나의 정보]");

    Member loginUser = AuthLogInHandler.getLoginUser();

    if (loginUser == null) {
      System.out.println("로그인 하지 않았습니다.");
      return;
    }

    switch(loginUser.getUserAccessLevel()) {
      case 0x02: 
        System.out.println("등급: 회원");
        break;

      case 0x04:
        System.out.println("등급: 회원");
        break;

      case 0x08: 
        System.out.println("등급: 회원");
        break;

      case 0x40:
        System.out.println("등급: 회원");
        break;

      case 0x10: 
        System.out.println("등급: 멘토");
        break;

      case 0x20: 
        System.out.println("등급: 관리자");
        break;
    }

    System.out.printf("이름: %s\n", loginUser.getName());
    System.out.printf("이메일: %s\n", loginUser.getEmail());
    System.out.printf("휴대폰 번호: %s\n", loginUser.getPhoneNumber());
    System.out.printf("가입일: %s\n", loginUser.getRegisteredDate());
  }
}
