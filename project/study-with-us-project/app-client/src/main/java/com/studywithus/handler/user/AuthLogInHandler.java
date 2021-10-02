package com.studywithus.handler.user;

import java.util.Collection;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class AuthLogInHandler implements Command {

  RequestAgent requestAgent;
  //  List<Member> memberList;

  public static Member loginUser;
  public static int userAccessLevel = Menu.ACCESS_LOGOUT;

  public static Member getLoginUser() {
    return loginUser;
  }

  public static int getUserAccessLevel() {
    return userAccessLevel;
  }

  public AuthLogInHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[로그인]\n");

    String email = Prompt.inputString("이메일: ");
    String password = Prompt.inputString("비밀번호: ");

    Member member = findByEmailPassword(email, password);

    if (email.equals("root") && password.equals("0000")) {
      Member root = new Member();

      root.setName("관리자");

      loginUser = root;
      userAccessLevel = Menu.ACCESS_ADMIN;

      System.out.println();
      System.out.printf("%s님 환영합니다.\n", root.getName());

      return;
    } 

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("아이디와 비밀번호가 일치하는 회원을 찾을 수 없습니다.\n");

    } else {
      System.out.println();
      member = requestAgent.getObject(Member.class);
      System.out.printf("%s님 환영합니다.\n", member.getName());

      loginUser = member;
      userAccessLevel = member.getUserAccessLevel();

      return;
    }
  }

  private Member findByEmailPassword(String id, String password) {
    Collection<Member> memberList = requestAgent.getObjects(Member.class);

    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(id) && member.getPassword().equals(password)) {
        return member;
      }
    }
    return null;
  }
}
