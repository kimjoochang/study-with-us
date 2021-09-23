package com.studywithus.handler.user;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class MembershipWithdrawalHandler extends AbstractLoginHandler {

  public MembershipWithdrawalHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[회원 탈퇴]");

    Member member = findById();

    String input = Prompt.inputString("정말 회원 탈퇴하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println(" 회원 탈퇴가 취소되었습니다.\n");
    }

    memberList.remove(member);
    AuthLogInHandler.userAccessLevel = Menu.ACCESS_LOGOUT;

    System.out.println();
    System.out.println("회원 탈퇴가 완료되었습니다.\n");

    return;
  }

  private Member findById() {
    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(AuthLogInHandler.getLoginUser().getId())) {
        return member;
      }
      return null;
    }
    return null;
  }
}
