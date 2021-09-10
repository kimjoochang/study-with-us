package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.util.Prompt;

public class FindIdHandler implements Command {

  List<Member> memberList;
  static Member loginUser;

  public static Member getLoginUser() {
    return loginUser;
  }

  public FindIdHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void execute() {
    System.out.println("[아이디 찾기]\n");

    String name = Prompt.inputString("이름? ");
    String phoneNumber = Prompt.inputString("핸드폰 번호? ");

    Member member = findIdByName(name, phoneNumber);

    if (member == null) {
      System.out.println(" ");
      System.out.println("해당 이름으로 가입된 아이디가 없습니다.\n");
      return;
    }
    System.out.printf("아이디: %s\n", member.getId());
  }

  // 아이디 찾기용
  private Member findIdByName(String name, String phoneNumber) {
    for (Member member : memberList) {
      if (member.getName().equals(name) && member.getPhoneNumber().equals(phoneNumber)) {
        return member;
      }
    }
    return null;
  }
}
