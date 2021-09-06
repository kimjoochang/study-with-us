package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.util.Prompt;

public class MemberHandler implements Command {

  List<Member> memberList;
  static Member loginUser;

  public static Member getLoginUser() {
    return loginUser;
  }

  public MemberHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void execute() {
    System.out.println("[로그인]\n");

    String id = Prompt.inputString("아이디? ");
    String pwd = Prompt.inputString("암호? ");

    Member member = findByIdPwd(id, pwd);

    if (member == null) {
      System.out.println("등록된 회원이 아닙니다.");

    } else {
      System.out.printf("%s님 환영합니다!\n", member.getName());
      loginUser = member;
    }
  }

  // 로그인용
  private Member findByIdPwd(String id, String pwd) {
    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(id) &&
          member.getPassword().equals(pwd)) {
        return member;
      }
    }
    return null;
  }
}
