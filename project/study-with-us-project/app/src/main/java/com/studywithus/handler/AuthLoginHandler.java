package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.NewMember;
import com.studywithus.util.Prompt;

public class AuthLoginHandler implements Command {

  List<NewMember> memberList;

  static NewMember loginUser;
  public static NewMember getLoginUser() {
    return loginUser;
  }

  public AuthLoginHandler(List<NewMember> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void execute() {
    System.out.println("[로그인]");

    String id = Prompt.inputString("아이디 : ");
    String password = Prompt.inputString("비밀번호 : ");

    NewMember member = findByIdPassword(id, password);

    if (member == null) {
      System.out.println("아이디와 비밀번호가 일치하는 회원을 찾을 수 없습니다.");
    } else {
      System.out.printf("%s님 환영합니다!\n", member.getName());
      loginUser = member;
    }
  }

  private NewMember findByIdPassword(String id, String password) {
    for (NewMember member : memberList) {
      if (member.getId().equalsIgnoreCase(id) &&
          member.getPwd().equals(password)) {
        return member;
      }
    }
    return null;
  }

}