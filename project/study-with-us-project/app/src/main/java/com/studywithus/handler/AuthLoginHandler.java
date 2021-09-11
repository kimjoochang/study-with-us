package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class AuthLoginHandler implements Command {

  List<Member> memberList;


  static Member loginUser;
  static int userAccessLevel = Menu.ACCESS_LOGOUT;

  public static Member getLoginUser() {
    return loginUser;
  }

  public static int getUserAccessLevel() {
    return userAccessLevel;
  }

  public AuthLoginHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void execute() {
    System.out.println("[로그인]");

    String id = Prompt.inputString("아이디 : ");
    String password = Prompt.inputString("비밀번호 : ");

    Member member = findByIdPassword(id, password);

    if (id.equals("root") && password.equals("0000")) {
      Member root = new Member();
      root.setName("관리자");
      root.setId("admin");
      loginUser = root;
      userAccessLevel = Menu.ACCESS_ADMIN;
      return;
    } 

    member = findByIdPassword(id, password);

    if (member == null) {
      System.out.println("아이디와 암호가 일치하는 회원을 찾을 수 없습니다.");
    } else {
      System.out.printf("%s님 환영합니다!\n", member.getName());
      loginUser = member;
      userAccessLevel = member.getUserAccessLevel();
    }
  }

  private Member findByIdPassword(String id, String password) {
    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(id) &&
          member.getPassword().equals(password)) {
        return member;
      }
    }
    return null;
  }
}
