package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.util.Prompt;

public class FindPwdHandler implements Command {

  List<Member> memberList;

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  public FindPwdHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void execute() {

    System.out.println("[비밀번호 변경]\n");
    String name = Prompt.inputString("회원 이름? ");
    String id = Prompt.inputString("회원 아이디? ");

    Member member = updatePwdById(name, id);

    if (member == null) {
      System.out.println(" ");
      System.out.println("해당 정보와 일치하는 회원이 없습니다.\n");
      return;
    }

    member.setPassword(Prompt.inputString("새 비밀번호를 입력해주세요: "));

    String input = Prompt.inputString("정말 변경하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println(" ");
      System.out.println("비밀번호 변경을 취소하였습니다.\n");
      return;
    }
  }

  // 비밀번호 변경용
  private Member updatePwdById(String name, String id) {
    for (Member member : memberList) {
      if (member.getName().equals(name) && member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }
}
