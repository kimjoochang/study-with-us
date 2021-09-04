package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.NewMember;
import com.studywithus.util.Prompt;

public class NewMemberHandler implements Command {

  List<NewMember> newMemberList;

  static NewMember loginUser;
  public static NewMember getLoginUser() {
    return loginUser;
  }

  public NewMemberHandler(List<NewMember> newMemberList) {
    this.newMemberList = newMemberList;

  }

  @Override
  public void execute() {
    System.out.println("[로그인]\n");

    String id = Prompt.inputString("아이디? ");
    String pwd = Prompt.inputString("암호? ");

    NewMember newMember = findByIdPassword(id, pwd);

    if (newMember == null) {
      System.out.println("등록된 회원이 아닙니다.");
    } else {
      System.out.printf("%s님 환영합니다!\n", newMember.getName());
      loginUser = newMember;
    }
  }

  // 로그인용
  private NewMember findByIdPassword(String id, String pwd) {
    for (NewMember newMember : newMemberList) {
      if (newMember.getId().equalsIgnoreCase(id) &&
          newMember.getPwd().equals(pwd)) {
        return newMember;
      }
    }
    return null;
  }

}
