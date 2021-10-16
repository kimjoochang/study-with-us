package com.studywithus.handler.user;

import java.util.HashMap;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class SnsLogInHandler implements Command {

  RequestAgent requestAgent;

  //  static Member loginUser;
  //  static int userAccessLevel = Menu.ACCESS_LOGOUT;
  //
  //  public static Member getLoginUser() {
  //    return loginUser;
  //  }
  //
  //  public static int getUserAccessLevel() {
  //    return userAccessLevel;
  //  }

  public SnsLogInHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[SNS 로그인]\n");

    String snsEmail = Prompt.inputString("아이디: ");
    String password = Prompt.inputString("비밀번호: ");

    HashMap<String,String> params = new HashMap<>();
    params.put("email", snsEmail);
    params.put("password", password);

    requestAgent.request("member.selectOneForLogin", params);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      Member member = requestAgent.getObject(Member.class);
      System.out.printf("%s님 환영합니다!\n", member.getName());

      /* 프로젝트에서 로그인한 회원의 정보는 
       * 모두 다 AuthLogInHandler.userAccessLevel를 확인하니까
       * snsLogin도 성공하면 AuthLoginHandler에 로그인한 회원 정보를 저장해야함
       * 기존코드 (15 ~24번째 줄은 주석처리)
       * member = AuthLogInHandler.getLoginUser();
       */
      AuthLogInHandler.loginUser = member;
      AuthLogInHandler.userAccessLevel = Menu.ACCESS_GENERAL;


    } else {
      System.out.println("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
    }

  }
}
