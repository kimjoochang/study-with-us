package com.studywithus.handler;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class SnsSignUpHandler extends AbstractLoginHandler {

  public SnsSignUpHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute(CommandRequest request) {

    Member member = new Member();

    String id = Prompt.inputString("SNS 계정: ");
    String password = Prompt.inputString("비밀번호: ");

    System.out.println();

    id = findById(id);

    if (id == null) {
      System.out.println("이미 연동된 SNS 계정입니다.");

    } else {
      member.setId(id);
      member.setPassword(password);
      member.setRegisteredDate((new Date(System.currentTimeMillis())));
      member.setUserAccessLevel(Menu.ACCESS_GENERAL);
      member.setRegisteredDate(new Date(System.currentTimeMillis()));

      memberList.add(member);

      System.out.println();
      System.out.println("SNS 계정 연동이 완료되었습니다.\n");
    }
  }

  private String findById(String id) {
    for (Member member : memberList) {
      if (id.equalsIgnoreCase(member.getId())) {
        return null;
      }
    }
    return id;
  }
}
