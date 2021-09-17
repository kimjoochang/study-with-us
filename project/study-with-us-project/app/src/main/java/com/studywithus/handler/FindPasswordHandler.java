package com.studywithus.handler;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.util.Prompt;

public class FindPasswordHandler implements Command {

  List<Member> memberList;

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  public FindPasswordHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void execute(CommandRequest request) {

    System.out.println("[비밀번호 변경]\n");
    String name = Prompt.inputString("이름? ");
    String id = Prompt.inputString("아이디? ");
    String phoneNumber = Prompt.inputString("핸드폰 번호? ");

    Member member = updatePwdById(name, id, phoneNumber);

    if (member == null) {
      System.out.println(" ");
      System.out.println("해당 정보와 일치하는 회원이 없습니다.\n");
      return;
    }

    while (true) {
      String newPassword1 = Prompt.inputString("새 비밀번호를 입력해주세요 : ");
      String newPassword2 = Prompt.inputString("다시 한 번 입력해주세요 : ");

      if (! newPassword1.equals(newPassword2)) {
        System.out.println("입력하신 비밀번호가 다릅니다.");
        continue;
      }

      member.setPassword(newPassword1);

      System.out.println("변경이 완료되었습니다.");
      return;
    }
  }

  // 비밀번호 변경용
  private Member updatePwdById(String name, String id, String phoneNumber) {
    for (Member member : memberList) {
      if (member.getName().equals(name) && member.getId().equals(id)
          && member.getPhoneNumber().equals(phoneNumber)) {
        return member;
      }
    }
    return null;
  }
}
