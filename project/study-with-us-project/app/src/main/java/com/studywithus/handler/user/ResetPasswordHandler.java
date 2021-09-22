package com.studywithus.handler.user;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ResetPasswordHandler implements Command {

  List<Member> memberList;

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  public ResetPasswordHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  @Override
  public void execute(CommandRequest request) {

    System.out.println("[비밀번호 변경]\n");
    String name = Prompt.inputString("회원 이름을 입력해주세요. > ");
    String phoneNumber = Prompt.inputString("휴대폰 번호를 입력해주세요. ('-'를 제외한 숫자 11자) > ");
    String id = Prompt.inputString("아이디를 입력해주세요.(이메일 형식의 아이디) > ");
    System.out.println(" ");

    Member member = resetPwdById(name, id, phoneNumber);

    if (member == null) {
      System.out.println(" ");
      System.out.println("해당 정보와 일치하는 회원이 없습니다.\n");
      return;
    }

    while (true) {
      String newPassword1 = Prompt.inputString("새 비밀번호를 입력하세요.(특수문자 !,@,$,^ 포함 8자 이상 16자 이하) > ");
      String newPassword2 = Prompt.inputString("새 비밀번호 확인을 입력하세요. > ");
      System.out.println(" ");

      // 현재 비밀번호와 새 비밀번호의 입력값이 같다면,
      if (newPassword1.equals(member.getPassword())) {
        System.out.println("현재와 동일한 비밀번호로 변경하실 수 없습니다.\n");
        continue;

      } else if (newPassword1.length() < 8 || newPassword1.length() > 16) {
        System.out.println("비밀번호는 8자 이상 16자 이하로 설정 가능합니다. \n");
        continue;

      } else if (!newPassword1.contains("!") && !newPassword1.contains("@") && !newPassword1.contains("$") && !newPassword1.contains("^")) {
        System.out.println("비밀번호는 다음의 특수문자를 하나 이상 포함해야 합니다.(!,@,$,^) \n");
        continue;

      }  else if (newPassword1.contains(id) == true) {
        System.out.println("아이디를 포함한 비밀번호는 사용하실 수 없습니다. \n");
        continue;

      }  else if (newPassword1.contains(phoneNumber) == true) {
        System.out.println("휴대폰 번호를 포함한 비밀번호는 사용하실 수 없습니다. \n");
        continue;

      }
      // 새 비밀번호 입력값과 새 비밀번호 입력값이 일치하지 않는다면,
      else if (! newPassword1.equals(newPassword2)) {
        System.out.println("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.\n");
        continue;
      } 

      while(true) {
        // 조건에 맞는 입력값을 주었을 때, 변경 의사 yes or no 확인
        String input = Prompt.inputString("비밀번호를 변경하시겠습니까? (y/N)");

        // n 혹은 공백 입력 시 비밀번호 변경 취소
        if (input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("비밀번호 변경을 취소하였습니다.\n");
          return;
          // y 입력시 비밀번호 변경 수행
        } else if (input.equalsIgnoreCase("y")) {

          member.setPassword(newPassword1);

          System.out.println(" ");
          System.out.println("비밀번호 변경이 완료되었습니다.\n");
          return;
          //y, n, 공백 제외 문자 입력 시 아래 문구 출력
        } else {
          System.out.println("올바른 값을 입력하세요.\n");
          continue;
        }
      }
    }
  }

  // 비밀번호 변경용
  private Member resetPwdById(String name, String id, String phoneNumber) {
    for (Member member : memberList) {
      if (member.getName().equals(name) && member.getId().equals(id)
          && member.getPhoneNumber().equals(phoneNumber)) {
        return member;
      }
    }
    return null;
  }
}
