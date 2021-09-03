package com.studywithus.handler2;

import com.studywithus.domain.NewMember;
import com.studywithus.util.Prompt;

public class NewMemberHandler {

  static final int MAX_LENGTH = 5;

  public NewMember[] newMembers = new NewMember[MAX_LENGTH];
  int size = 0;

  public NewMember[] add(NewMember[] loginInfo) {
    NewMember newMember = new NewMember();

    newMember.setName(Prompt.inputString("이름을 입력해주세요: "));
    newMember.setId(Prompt.inputString("사용할 아이디를 입력해주세요: "));
    newMember.setPwd(Prompt.inputString("사용할 비밀번호를 입력해주세요: "));

    System.out.println();
    System.out.println("회원가입이 완료되었습니다.");

    loginInfo[this.size++] = newMember;
    return loginInfo;
  }

  // 김제이.....ing

  public void findMemberId() {

    System.out.println("[아이디 찾기]\n");
    String name = Prompt.inputString("회원 이름? ");

    NewMember n = findIdByName(name);

    if (n == null) {
      System.out.println(" ");
      System.out.println("해당 이름으로 가입된 아이디가 없습니다.\n");
      return;
    }

    System.out.printf("아이디: %s\n", n.getId());
  }

  public void updateMemberPwd() {

    System.out.println("[비밀번호 찾기]\n");
    String name = Prompt.inputString("회원 이름? ");
    String id = Prompt.inputString("회원 아이디? ");

    NewMember n = findIdByName(name);
    NewMember i = updatePwdById(name, id);

    if (n == null && i == null) {
      System.out.println(" ");
      System.out.println("해당 정보와 일치하는 회원이 없습니다.\n");
      return;
    }

    n.setPwd(Prompt.inputString("새 비밀번호를 입력해주세요: "));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println(" ");
      System.out.println("비밀번호 변경을 취소하였습니다.\n");
      return;
    }
  }

  private NewMember findIdByName(String name) {
    for (int i = 0; i < this.size; i++) {
      if (this.newMembers[i].getName() == name) {
        return this.newMembers[i];
      }
    }
    return null;
  }

  private NewMember updatePwdById(String name, String id) {
    for (int i = 0; i < this.size; i++) {
      if (this.newMembers[i].getName() == name && this.newMembers[i].getId() == id) {
        return this.newMembers[i];
      }
    }
    return null;
  }

}
