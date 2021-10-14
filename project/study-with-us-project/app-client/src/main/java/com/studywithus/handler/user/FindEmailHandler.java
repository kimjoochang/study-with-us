package com.studywithus.handler.user;

import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class FindEmailHandler implements Command {

  MemberDao memberDao;

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  public FindEmailHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[아이디 찾기]\n");

    String name = Prompt.inputString("이름을 입력하세요. > ");
    String phoneNumber = Prompt.inputString("휴대폰 번호를 입력하세요. > ");
    //    Member loginUser = AuthLogInHandler.getLoginUser();


    Member member = memberDao.findMemberByNamePhoneNumber(name,phoneNumber);


    if (member == null) {
      System.out.println();
      System.out.println("입력하신 정보와 일치하는 회원을 찾을 수 없습니다.\n");
      return;
    }

    //requestAgent.request("member.showEmail", params);
    System.out.println();
    System.out.println(name + " 회원님의 아이디는 " + member.getEmail() + " 입니다.\n");
  }
}
