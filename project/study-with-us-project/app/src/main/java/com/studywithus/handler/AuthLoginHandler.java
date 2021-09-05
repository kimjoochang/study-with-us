package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.util.Prompt;

public class AuthLoginHandler implements Command {

  List<Member> memberList;

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
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

    if (Member.getAdminId().equals(id) && Member.getAdminPwd().equals(password)) {
      System.out.println("관리자님 환영합니다!");
    } else if(member == null) {
      System.out.println("아이디와 비밀번호가 일치하는 회원을 찾을 수 없습니다.");
    } else {
      System.out.printf("%s님 환영합니다!\n", member.getName());
      loginUser = member;
    }
  }

  private Member findByIdPassword(String id, String password) {
    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(id) &&
          member.getPwd().equals(password)) {
        return member;
      }
    }
    return null;
  }
}
