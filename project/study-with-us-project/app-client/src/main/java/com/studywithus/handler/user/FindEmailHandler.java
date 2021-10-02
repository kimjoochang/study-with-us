package com.studywithus.handler.user;

import java.util.HashMap;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class FindEmailHandler implements Command {

  RequestAgent requestAgent;

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  public FindEmailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[아이디 찾기]\n");

    String name = Prompt.inputString("이름을 입력하세요. > ");
    String phoneNumber = Prompt.inputString("휴대폰 번호를 입력하세요. > ");
    //    Member loginUser = AuthLogInHandler.getLoginUser();

    HashMap<String,String> params = new HashMap<>();
    params.put("name",name);
    params.put("phoneNumber", phoneNumber);

    requestAgent.request("member.selectOneByNamePhoneNumber", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println();
      System.out.println("입력하신 정보와 일치하는 회원을 찾을 수 없습니다.\n");
      return;
    }

    Member member = requestAgent.getObject(Member.class);

    //requestAgent.request("member.showEmail", params);
    System.out.println();
    System.out.println(name + " 회원님의 아이디는 " + member.getEmail() + " 입니다.\n");
  }
}
