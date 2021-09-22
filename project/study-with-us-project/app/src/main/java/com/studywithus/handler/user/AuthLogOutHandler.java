package com.studywithus.handler.user;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class AuthLogOutHandler implements Command {

  public AuthLogOutHandler(List<Member> memberlist) {
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[로그아웃]\n");

    String input = Prompt.inputString("로그아웃하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("로그아웃을 취소하였습니다.\n");
      return;
    } 

    // 현재 로그인된 회원의 권한을 setUserAccessLevel에 저장 후 로그아웃
    AuthLogInHandler.loginUser.setUserAccessLevel(AuthLogInHandler.userAccessLevel);

    AuthLogInHandler.loginUser = null;
    AuthLogInHandler.userAccessLevel = Menu.ACCESS_LOGOUT;

    System.out.println("로그아웃이 완료되었습니다.\n");
  }
}
