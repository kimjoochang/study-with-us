package com.studywithus.handler.user;

import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class AuthLogInHandler implements Command {

  MemberDao memberDao;

  public static Member loginUser;
  public static int userAccessLevel = Menu.ACCESS_LOGOUT;

  public static Member getLoginUser() {
    return loginUser;
  }

  public static int getUserAccessLevel() {
    return userAccessLevel;
  }

  public AuthLogInHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[로그인]\n");

    String email = Prompt.inputString("이메일: ");
    String password = Prompt.inputString("비밀번호: ");

    if (email.equals("root@test.com") && password.equals("0000")) {
      Member root = new Member();
      root.setName("관리자");
      root.setEmail("root@test.com");
      loginUser = root;
      userAccessLevel = Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL;
      System.out.println("관리자 계정으로 로그인하였습니다.\n");
      return;
    } 

    Member member = memberDao.findMemberByEmailPassword(email, password);

    if (member != null) {
      System.out.printf("%s님 환영합니다!\n", member.getName());
      loginUser = member;
      userAccessLevel = Menu.ACCESS_GENERAL;

    } else {
      System.out.println("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
    }
  }
}
