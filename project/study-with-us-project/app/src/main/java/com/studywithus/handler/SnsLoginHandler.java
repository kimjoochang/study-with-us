package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class SnsLoginHandler implements Command {

  List<Member> memberList;

  static Member loginUser;
  static int userAccessLevel = Menu.ACCESS_LOGOUT;

  public static Member getLoginUser() {
    return loginUser;
  }

  public static int getUserAccessLevel() {
    return userAccessLevel;
  }

  public SnsLoginHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[SNS 로그인]");

    String id = Prompt.inputString("아이디: ");
    String password = Prompt.inputString("비밀번호: ");

    Member member = findByIdPassword(id, password);

    if (member == null) {
      System.out.println("연동된 SNS 계정이 존재하지 않습니다.");

    } else {
      System.out.printf("%s님 환영합니다.\n", member.getName());

      loginUser = member;
      userAccessLevel = member.getUserAccessLevel();
    }
  }

  private Member findByIdPassword(String id, String password) {
    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(id) && member.getPassword().equals(password)) {
        return member;
      }
    }
    return null;
  }
}
