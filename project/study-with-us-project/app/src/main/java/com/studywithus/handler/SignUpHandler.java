package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.util.Prompt;

public class SignUpHandler extends AbstractLoginHandler {

  public SignUpHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute() {

    Member member = new Member();

    member.setName(Prompt.inputString("이름? "));
    member.setId(Prompt.inputString("아이디? "));
    member.setPwd(Prompt.inputString("비밀번호? "));

    memberList.add(member);

    System.out.println();
    System.out.println("회원가입이 완료되었습니다.\n");
  }

}
