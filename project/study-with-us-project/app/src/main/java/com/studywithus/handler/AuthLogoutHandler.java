package com.studywithus.handler;

import com.studywithus.domain.Member;
import com.studywithus.menu.Menu;

public class AuthLogoutHandler implements Command {

  Member member = new Member();

  @Override
  public void execute() {
    System.out.println("[로그아웃]");

    // 로그아웃 시 변경된 권한 업데이트
    member.setUserAccessLevel(AuthLoginHandler.userAccessLevel);

    AuthLoginHandler.loginUser = null;
    AuthLoginHandler.userAccessLevel = Menu.ACCESS_LOGOUT;
    System.out.println("로그아웃 하였습니다.");
  }
}