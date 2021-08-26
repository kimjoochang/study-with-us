package com.studywithus;

import com.studywithus.handler.CommunityHandler;
import com.studywithus.handler.FreeStudyHandler;
import com.studywithus.util.Prompt;

public class App {

  public static void main(String[] args) {
    FreeStudyHandler freeStudyHandler = new FreeStudyHandler();
    CommunityHandler communityHandler = new CommunityHandler();

    Main : while(true) {
      System.out.println();
      System.out.println("[스터디위더스]\n");
      System.out.println("1. 비회원으로 시작하기");
      System.out.println("2. 로그인");
      System.out.println("3. 회원가입");
      System.out.println("0. 종료하기\n");
      int input = Prompt.inputInt("메뉴를 선택해주세요 > \n");

      switch(input) {

        case 1 :
          System.out.println("1. 무료스터디 조회");
          System.out.println("2. 유료스터디 조회");
          System.out.println("3. 커뮤니티 조회");
          System.out.println("4. 캘린더 조회");
          System.out.println("0. 이전\n");
          input = Prompt.inputInt("번호를 입력해주세요 > \n");
          switch(input) {
            case 1 : freeStudyHandler.list();
            case 2 : break;
            case 3 : communityHandler.list();
            case 0 : continue Main;
            default : System.out.println("잘못된 번호입니다");
            break;
          }
          break;

        case 2 :
          String Id = Prompt.inputString("아이디 입력 :");
          String Pwd = Prompt.inputString("비밀번호 입력 :");
          break;

        case 3 :
          // 회원가입 데이터 도메인 만들고 인스턴스에 저장
          break;

        case 4 : System.out.println("종료되었습니다.");
        break Main;
        default : System.out.println("잘못된 번호입니다");
        continue;
      }
    }
  }
}