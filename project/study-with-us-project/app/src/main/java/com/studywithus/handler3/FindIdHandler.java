package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.NewMember;
import com.studywithus.util.Prompt;

public class FindIdHandler implements Command {

  List<NewMember> newMemberList;

  static NewMember loginUser;
  public static NewMember getLoginUser() {
    return loginUser;
  }

  public FindIdHandler(List<NewMember> newMemberList) {
    this.newMemberList = newMemberList;

  }

  @Override
  public void execute() {

    System.out.println("[아이디 찾기]\n");
    String name = Prompt.inputString("회원 이름? ");

    NewMember newMember = findIdByName(name);

    if (newMember == null) {
      System.out.println(" ");
      System.out.println("해당 이름으로 가입된 아이디가 없습니다.\n");
      return;
    }
    System.out.printf("아이디: %s\n", newMember.getId());
  }


  // 아이디 찾기용
  private NewMember findIdByName(String name) {
    for (NewMember newMember : newMemberList) {
      if (newMember.getName().equals(name)) {
        return newMember;
      }
    }
    return null;
  }


}
