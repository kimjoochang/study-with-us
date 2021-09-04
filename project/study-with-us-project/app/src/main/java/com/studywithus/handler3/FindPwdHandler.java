package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.NewMember;
import com.studywithus.util.Prompt;

public class FindPwdHandler implements Command {

  List<NewMember> newMemberList;

  static NewMember loginUser;
  public static NewMember getLoginUser() {
    return loginUser;
  }

  public FindPwdHandler(List<NewMember> newMemberList) {
    this.newMemberList = newMemberList;

  }

  @Override
  public void execute() {

    System.out.println("[비밀번호 변경]\n");
    String name = Prompt.inputString("회원 이름? ");
    String id = Prompt.inputString("회원 아이디? ");

    NewMember newMember = updatePwdById(name, id);

    if (newMember == null) {
      System.out.println(" ");
      System.out.println("해당 정보와 일치하는 회원이 없습니다.\n");
      return;
    }

    newMember.setPwd(Prompt.inputString("새 비밀번호를 입력해주세요: "));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println(" ");
      System.out.println("비밀번호 변경을 취소하였습니다.\n");
      return;
    }
  }

  // 비밀번호 변경용
  private NewMember updatePwdById(String name, String id) {
    for (NewMember newMember : newMemberList) {
      if (newMember.getName().equals(name) && newMember.getId().equals(id)) {
        return newMember;
      }
    }
    return null;
  }

}
