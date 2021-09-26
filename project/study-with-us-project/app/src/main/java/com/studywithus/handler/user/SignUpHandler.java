package com.studywithus.handler.user;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class SignUpHandler extends AbstractLoginHandler {

  public SignUpHandler(List<Member> memberList) {
    super(memberList);
  }

  @Override
  public void execute(CommandRequest request) {

    Member member = new Member();

    // 이용약관
    System.out.println("-----------------------------------------------------------------------------------------------");
    System.out.println("1장. 약관 및 계약\r\n"
        + "\n제1조. 목적\r\n"
        + "\r\n"
        + "이 이용약관(이하 ‘약관’)은 스터디위더스(이하 ‘회사’)가 제공하는 스터디위더스(studywithus.com)\n"
        + " 및 스터디위더스 관련 제반 서비스(이하 ‘서비스’)를 이용고객(이하 ‘회원’)이 이용함에 있어 회사와\n "
        + "회원과의 권리, 의무 및 책임사항, 기타 필요한 사항을 구체적으로 규정함을 목적으로 합니다.\r\n"
        + "\r\n"
        + "제2조. 용어의 정의\r\n"
        + "\r\n"
        + "이 약관에서 사용하는 용어의 정의는 다음과 같습니다.\r\n"
        + "“서비스”라 함은 구현되는 단말기와 상관없이 “회원” 혹은 웹사이트 방문자가 이용할 수 있는\n "
        + "스터디위더스 서비스를 의미합니다.\r\n"
        + "“회원”이란 “회사”의 서비스에 접속하여 본 약관에 따라 회사와 이용계약을 체결하고 “회사”가\n "
        + "제공하는 “서비스”를 이용하는 고객을 말합니다.\r\n"
        + "“이용계약”이란 이 약관을 포함하여 서비스 이용과 관련하여 회사와 회원 간에 체결하는 모든\n "
        + "계약을 말합니다.\r\n"
        + "“아이디(ID)”라 함은 회원의 식별 및 서비스 이용을 위하여 회원의 신청에 따라 회사가 회원\n "
        + "별로 부여하는 고유한 문자와 숫자의 조합을 말합니다.\r\n"
        + "“이메일”은 회원가입이나 로그인 등에 사용되는 단일한 이메일 주소를 말합니다.\r\n"
        + "“비밀번호(Password)”란 아이디(ID)로 식별되는 회원의 본인 여부를 검증하기 위하여 회원이\n "
        + "설정하여 회사에 등록한 고유의 문자와 숫자의 조합을 말합니다.\r\n"
        + "“유료서비스”라 함은 회사가 유료로 제공하는 각종 온라인디지털콘텐츠 및 서비스를 의미합니다.\r\n"
        + "“해지”라 함은 회사 또는 회원이 이용계약을 해약하는 것을 말합니다.\r\n"
        + "“게시물”이라 함은 “회원”이 “서비스”를 이용함에 있어 “서비스” 상에 게시한 글, 사진, 동영상\n"
        + " 및 각종 파일과 링크를 의미합니다.\r\n"
        + "이 약관에서 사용하는 용어 중 제1항에서 정하지 아니한 것은 관계 법령 및 서비스 별 안내에서\n"
        + "정하는 바에 따르며, 그 외에는 일반 관례에 따릅니다.\n");
    System.out.println("-----------------------------------------------------------------------------------------------");

    while (true) {
      System.out.println();
      String input = Prompt.inputString("스터디위더스 이용약관에 동의하고 회원가입을 진행하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("회원가입을 취소하였습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {

        while (true) {

          System.out.println();
          String name = Prompt.inputString("이름을 입력하세요. > ");
          String phoneNumber = Prompt.inputString("휴대폰 번호를 입력하세요.('-'를 제외한 숫자 11자) > ");
          String id = Prompt.inputString("사용할 아이디를 입력하세요.(이메일 형식의 아이디) > ");
          String password = Prompt.inputString("사용할 비밀번호를 입력하세요.(특수문자 !,@,$,^ 포함 8자 이상 16자 이하) > ");

          id = findById(id);

          if (id == null) {
            System.out.println("중복된 아이디가 있습니다.\n");
            return;
          } else if (!id.contains("@") || !id.contains(".com")){
            System.out.println("\n이메일 형식의 아이디를 입력하세요.\n");
            return;
          } else if (password.length() < 8 || password.length() > 16) {
            System.out.println("\n비밀번호는 8자 이상 16자 이하로 설정 가능합니다.\n");
            return;
          } else if (!password.contains("!") && !password.contains("@") && !password.contains("$") && !password.contains("^")) {
            System.out.println("\n비밀번호는 다음의 특수문자를 하나 이상 포함해야 합니다.(!,@,$,^)\n");
            return;
          }  else if (password.contains(id) == true) {
            System.out.println("\n아이디를 포함한 비밀번호는 사용하실 수 없습니다.\n");
            return;
          }  else if (password.contains(phoneNumber) == true) {
            System.out.println("\n휴대폰 번호를 포함한 비밀번호는 사용하실 수 없습니다.\n");
            return;
          } else if (phoneNumber.length() != 11) {
            System.out.println("\n올바른 형식의 휴대폰 번호를 입력하세요.\n");
            return;
          } else {
            member.setName(name);
            member.setId(id);
            member.setPassword(password);
            member.setPhoneNumber(phoneNumber);
            member.setRegisteredDate((new Date(System.currentTimeMillis())));
            member.setUserAccessLevel(Menu.ACCESS_GENERAL);
            member.setRegisteredDate(new Date(System.currentTimeMillis()));

            memberList.add(member);

            System.out.println("회원가입이 완료되었습니다.\n");
            return;
          }
        }
      } else {
        System.out.println("올바른 값을 입력하세요.\n");
        return;
      }
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
