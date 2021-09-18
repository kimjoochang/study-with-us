package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.menu.Menu;

public class AuthLogOutHandler implements Command {

  public AuthLogOutHandler(List<Member> memberlist) {
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[로그아웃]");

    // 현재 로그인된 회원의 권한을 setUserAccessLevel에 저장 후 로그아웃
    AuthLogInHandler.loginUser.setUserAccessLevel(AuthLogInHandler.userAccessLevel);

    AuthLogInHandler.loginUser = null;
    AuthLogInHandler.userAccessLevel = Menu.ACCESS_LOGOUT;

    System.out.println("로그아웃 하였습니다.");
  }
}
