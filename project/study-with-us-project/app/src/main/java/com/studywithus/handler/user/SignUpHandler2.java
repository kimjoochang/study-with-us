package com.studywithus.handler.user;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class SignUpHandler2 extends AbstractLoginHandler {

  public SignUpHandler2(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute(CommandRequest request) {

    Member member = new Member();

    String name = Prompt.inputString("이름을 입력하세요. > ");
    String phoneNumber = Prompt.inputString("휴대폰 번호를 입력하세요.('-'를 제외한 숫자 11자) > ");
    String id = Prompt.inputString("사용할 아이디를 입력하세요.(이메일 형식의 아이디) >");
    String password = Prompt.inputString("사용할 비밀번호를 입력하세요.(특수문자 !,@,$,^ 포함 8자 이상 16자 이하) > ");

    System.out.println();

    id = findById(id);

    if (id == null) {
      System.out.println("중복된 아이디가 있습니다.\n");

    } else {
      member.setName(name);
      member.setId(id);
      member.setPassword(password);
      member.setPhoneNumber(phoneNumber);
      member.setRegisteredDate((new Date(System.currentTimeMillis())));
      member.setUserAccessLevel(Menu.ACCESS_GENERAL);
      member.setRegisteredDate(new Date(System.currentTimeMillis()));

      memberList.add(member);

      System.out.println();
      System.out.println("회원가입이 완료되었습니다.\n");
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
