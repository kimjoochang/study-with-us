package com.studywithus;

import com.studywithus.domain.NewMember;
import com.studywithus.handler.MentorApplicantAddHandler;
import com.studywithus.handler.NewMemberHandler;
import com.studywithus.menuList.ChargeStudyMenuList;
import com.studywithus.menuList.CommunityMenuList;
import com.studywithus.menuList.FreeStudyMenuList;
import com.studywithus.menuList.InterestMenuList;
import com.studywithus.menuList.MenuList;

public class App {

  public static void main(String[] args) {

    MenuList menuList = new MenuList();
    NewMemberHandler newMemberHandler = new NewMemberHandler();
    FreeStudyMenuList freeStudyMenuList = new FreeStudyMenuList();
    ChargeStudyMenuList chargeStudyMenuList = new ChargeStudyMenuList();
    CommunityMenuList communityMenuList = new CommunityMenuList();
    InterestMenuList interestMenuList = new InterestMenuList();
    MentorApplicantAddHandler mentorApplicantAddHandler = new MentorApplicantAddHandler(null);

    NewMember[] loginInfo = new NewMember[5];

    Main : while(true) {
      int input = menuList.mainMenuList();

      if (input == 0) {
        System.out.println("종료되었습니다.\n");
        return;

      } else if (input == 1) { // 비회원 조회하기
        menuList.noneMemberMenuList();
        continue Main;

      } else if (input == 2) { // 로그인
        try {
          input = menuList.loginExecute(loginInfo);
        } catch (Exception e) {
          System.out.println();
          System.out.println("일치하는 로그인 정보가 없습니다.\n");
          menuList.findMemberInfoList();
          continue;
        }

        if (input == 0){
          continue Main;

          // 관리자 메뉴
        } else if (input == 2) {
          menuList.adminMenuList();
          continue Main;
        }
        // 로그인 성공 후 메뉴
        Member : while(true) {
          input = menuList.memberMenuList(); 

          if (input == 0) {
            System.out.println("로그아웃이 완료되었습니다.\n");
            continue Main;

            // 1. 무료 스터디 메뉴
          } else if (input == 1) {
            freeStudyMenuList.freeStudyMenuList();
            continue Member;

            // 2. 유료 스터디 메뉴
          } else if (input == 2) {
            chargeStudyMenuList.chargeStudyMenuList();
            continue Member;

            // 관심목록 메뉴
          } else if (input == 3) {
            interestMenuList.interestMenuList();
            continue Member;

            // 커뮤니티 메뉴
          } else if (input == 4) {
            Community : while (true) {
              input = communityMenuList.communityMainMenuList();

              if(input == 1) { // 커뮤니티-질문 메뉴                
                communityMenuList.communityQaMenuList();
                continue Community;                 

              } else if(input == 2) { //커뮤니티-정보 메뉴               
                communityMenuList.communityInfoMenuList();
                continue Community;

              } else if(input == 3) { //커뮤니티-스몰톡 메뉴                
                communityMenuList.communityTalkMenuList();
                continue Community;  

              } else if(input == 0) { // 커뮤니티 메뉴에서 이전
                continue Member;

              } else {
                System.out.println("잘못된 번호입니다.\n");
                continue Community;
              }
            }
          }// 3. 관심목록 메뉴          
          else if (input == 5) { // 캘린더
            // [추가]

          } else if (input == 6) { // 멘토 신청하기
            mentorApplicantAddHandler.execute();

          } else {
            System.out.println("잘못된 번호입니다.\n");
            continue;
          }
        }

        // 회원가입
      } else if (input == 3) {
        newMemberHandler.add(loginInfo);   
        continue;

        // 아이디 / 비밀번호 찾기
      } else if (input == 4) {
        menuList.findMemberInfoList();
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
      }
    }
  }

}

