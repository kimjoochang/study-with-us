package com.studywithus.handler.user;

import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.request.RequestAgent;

public class AuthLogOutHandler implements Command {

  RequestAgent requestAgent;

  public  AuthLogOutHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[로그아웃]\n");

    // 현재 로그인된 회원의 권한을 setUserAccessLevel에 저장 후 로그아웃
    AuthLogInHandler.loginUser.setUserAccessLevel(AuthLogInHandler.getUserAccessLevel());

    AuthLogInHandler.loginUser = null;
    AuthLogInHandler.userAccessLevel = Menu.ACCESS_LOGOUT;

    System.out.println("로그아웃이 완료되었습니다.\n");
  }
}
