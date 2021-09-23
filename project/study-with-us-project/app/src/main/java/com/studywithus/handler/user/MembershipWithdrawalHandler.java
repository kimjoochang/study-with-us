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
    System.out.println("[회원 탈퇴]\n");

    System.out.println("회원 탈퇴를 위해 회원 정보를 다시 한 번 입력해주세요.\n");

    String id = Prompt.inputString("아이디: ");
    String password = Prompt.inputString("비밀번호: ");

    Member member = findByIdPassword(id, password);
    Member loginUser = AuthLogInHandler.getLoginUser();

    if (!loginUser.getId().equals(id)) {
      System.out.println();
      System.out.println("현재 로그인한 아이디와 일치하지 않습니다.\n");
      return;
    }
    else if (member == null) {
      System.out.println();
      System.out.println("아이디와 비밀번호가 일치하는 회원을 찾을 수 없습니다.\n");
      return;
    } else {
      System.out.println();
      String input = Prompt.inputString("정말 회원 탈퇴하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println();
        System.out.println(" 회원 탈퇴가 취소되었습니다.\n");
      } else {
        memberList.remove(member);
        AuthLogInHandler.userAccessLevel = Menu.ACCESS_LOGOUT;
        System.out.println();
        System.out.println("회원 탈퇴가 완료되었습니다.\n");
        return;
      }
    }

  }

  //  private Member findById() {
  //    for (Member member : memberList) {
  //      if (member.getId().equalsIgnoreCase(AuthLogInHandler.getLoginUser().getId())) {
  //        return member;
  //      }
  //      return null;
  //    }
  //    return null;
  //  }

  private Member findByIdPassword(String id, String password) {
    for (Member member : memberList) {
      if (member.getId().equalsIgnoreCase(id) && member.getPassword().equals(password)) {
        return member;
      }
    }
    return null;
  }
}
