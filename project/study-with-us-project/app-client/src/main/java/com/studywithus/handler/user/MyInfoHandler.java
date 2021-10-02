package com.studywithus.handler.user;

import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;

public class MyInfoHandler implements Command {

  RequestAgent requestAgent;

  public MyInfoHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[나의 정보]\n");

    requestAgent.request("member.myInfo", null);

    //    Member loginUser = AuthLogInHandler.getLoginUser();

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("로그인 후 이용 바랍니다.");
      return;
    }

    //    if (loginUser == null) {
    //      System.out.println("로그인 후 이용 바랍니다.");
    //      return;
    //    }

    Member user =  requestAgent.getObject(Member.class);

    switch(user.getUserAccessLevel()) {
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

    System.out.printf("이름: %s\n", user.getName());
    System.out.printf("이메일: %s\n", user.getEmail());
    System.out.printf("휴대폰 번호: %s\n", user.getPhoneNumber());
    System.out.printf("가입일: %s\n", user.getRegisteredDate());
  }
}
