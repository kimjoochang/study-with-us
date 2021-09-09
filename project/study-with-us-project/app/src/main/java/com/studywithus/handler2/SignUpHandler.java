package com.studywithus.handler;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class SignUpHandler extends AbstractLoginHandler {

  public SignUpHandler(List<Member> memberList) {
    super(memberList);
  }

  Member member = new Member();

  @Override
  public void execute() {
    String name = Prompt.inputString("이름? ");
    String id = Prompt.inputString("아이디? ");
    String password = Prompt.inputString("비밀번호? ");
    String phoneNumber = Prompt.inputString("핸드폰번호? ");
    Date registerdDate = (new Date(System.currentTimeMillis()));
    System.out.println();

    id = findById(id);

    if (id == null) {

      System.out.println("중복된 아이디가 있습니다.");
    } else {
      member.setName(name);
      member.setId(id);
      member.setPassword(password);
      member.setPhoneNumber(phoneNumber);
      member.setRegisteredDate((new Date(System.currentTimeMillis())));
      member.setUserAccessLevel(Menu.ACCESS_GENERAL);
      memberList.add(member);
      System.out.println();
      System.out.println("회원가입이 완료되었습니다.\n");
    }
  }

  private String findById(String id) {
    for(Member member : memberList) {
      if(id.equalsIgnoreCase(member.getId())) {
        return null;
      }
    }
    return id;
  }
}