package com.studywithus.handler.user;

import java.util.HashMap;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class AuthLogInHandler implements Command {

  RequestAgent requestAgent;
  //  List<Member> memberList;

  public static Member loginUser;
  public static int userAccessLevel = Menu.ACCESS_LOGOUT;

  public static Member getLoginUser() {
    return loginUser;
  }

  public static int getUserAccessLevel() {
    return userAccessLevel;
  }

  public AuthLogInHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[로그인]\n");

    String email = Prompt.inputString("이메일: ");
    String password = Prompt.inputString("비밀번호: ");

    //    Member member = findByEmailPassword(email, password);

    if (email.equals("root@test.com") && password.equals("0000")) {
      Member root = new Member();
      root.setName("관리자");
      root.setEmail("root@test.com");
      loginUser = root;
      userAccessLevel = Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL;
      System.out.println("관리자 계정으로 로그인하였습니다.\n");
      return;
    } 

    HashMap<String,String> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);

    requestAgent.request("member.selectOneForLogin", params);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      Member member = requestAgent.getObject(Member.class);
      System.out.printf("%s님 환영합니다!\n", member.getName());
      loginUser = member;
      userAccessLevel = Menu.ACCESS_GENERAL;

    } else {
      System.out.println("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
    }
  }
}

//  private Member findByEmailPassword(String id, String password) {
//    Collection<Member> memberList = requestAgent.getObjects(Member.class);
//
//    for (Member member : memberList) {
//      if (member.getId().equalsIgnoreCase(id) && member.getPassword().equals(password)) {
//        return member;
//      }
//    }
//    return null;
//  }
