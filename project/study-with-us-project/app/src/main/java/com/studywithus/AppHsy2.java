package com.studywithus;

import com.studywithus.domain.NewMember;
import com.studywithus.handler.MentorHandler;
import com.studywithus.handler.NewMemberHandler;
import com.studywithus.menuList.MenuList;

public class AppHsy2 {

  public static void main(String[] args) {

    MentorHandler mentorHandler = new MentorHandler();
    NewMemberHandler newMemberHandler = new NewMemberHandler();
    NewMember[] loginInfo = new NewMember[5];
    MenuList menuList = new MenuList();

    Main : while(true) {
      int input = menuList.mainMenuList();
      if (input == 0) {
        System.out.println("종료되었습니다.");
        return;
      } else if (input == 1) { // 비회원 조회하기
        menuList.noneMemberMenuList();
        continue Main;
      } else if (input == 2) { // 로그인 s
        input = menuList.loginExecute(loginInfo);

        if (input == 0){
          continue Main;
        } else if (input == 2) {
          int adminInput = menuList.adminMenuList();
        }

        // 로그인 성공 후 메뉴
        Member : while(true) {
          input = menuList.memberMenuList(); 
          if (input == 0) {
            System.out.println("로그아웃이 완료되었습니다.");
            continue Main;
          } else if (input == 1) { // 무료 스터디 메뉴
            menuList.freeStudyMenuList();
            continue Member;
          }else if (input == 2) { // 유료스터디 메뉴
            menuList.chargeStudyMenuList();
            continue Member;
          } else if (input == 3) { // 관심목록 메뉴
            menuList.interestMenuList();
            continue Member;
          } else if (input == 5) {
            mentorHandler.add();
          } else if (input == 4) { // 커뮤니티 메뉴
            Community : while (true) {
              input = menuList.communityMenuList();

              if(input == 1) { // 커뮤니티-질문 메뉴 
                System.out.println("[커뮤니티 / 질문]\n");
                menuList.communityQaMenuList();
                continue Community;                 
              } else if(input == 2) { //커뮤니티-정보 메뉴
                System.out.println("[커뮤니티 / 정보]\n");
                menuList.communityInfoMenuList();
                continue Community;
              } else if(input == 3) { //커뮤니티-스몰톡 메뉴
                System.out.println("[커뮤니티 / 스몰톡]\n");
                menuList.communityTalkMenuList();
                continue Community;  
              } else if(input == 0) { // 커뮤니티 메뉴에서 이전
                continue Member;
              } else {
                System.out.println("잘못된 번호입니다.");
                continue Community;
              }
            }
          }else {
            System.out.println("잘못된 번호입니다.");
            continue;
          }
        }

        // 회원가입
      } else if (input == 3) {
        loginInfo = newMemberHandler.add(loginInfo);
        continue;

        // 메인 메뉴에서 종료
      } else if (input == 4) {
        System.out.println("종료되었습니다.");
        return;
      } else {
        System.out.println("잘못된 번호입니다.");
      }
    }
  }





}