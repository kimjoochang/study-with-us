package com.studywithus.handler;

import com.studywithus.menu.Menu;

public class AuthLogoutHandler implements Command {

  @Override
  public void execute() {
    System.out.println("[로그아웃]");

    AuthLoginHandler.loginUser = null;
    AuthLoginHandler.userAccessLevel = Menu.ACCESS_LOGOUT;
    System.out.println("로그아웃 하였습니다.");
  }
}