package com.studywithus;

import com.studywithus.domain.NewMember;
import com.studywithus.handler.MentorApplicantAddHandler;
import com.studywithus.handler.NewMemberHandler;
import com.studywithus.menuList.CalenderMenuList;
import com.studywithus.menuList.ChargeStudyMenuList;
import com.studywithus.menuList.CommunityMenuList;
import com.studywithus.menuList.FreeStudyMenuList;
import com.studywithus.menuList.InterestMenuList;
import com.studywithus.menuList.MenuList;

public class App2 {

  public static void main(String[] args) {

    MenuList menuList = new MenuList();
    NewMemberHandler newMemberHandler = new NewMemberHandler();
    FreeStudyMenuList freeStudyMenuList = new FreeStudyMenuList();
    CalenderMenuList CalenderMenuList = new CalenderMenuList();
    ChargeStudyMenuList chargeStudyMenuList = new ChargeStudyMenuList();
    CommunityMenuList communityMenuList = new CommunityMenuList();
    InterestMenuList interestMenuList = new InterestMenuList();
    MentorApplicantAddHandler mentorApplicantAddHandler = new MentorApplicantAddHandler(null);

    NewMember[] loginInfo = new NewMember[5];

    Main : while (true) {
      int input = menuList.mainMenuList();

      // 1. 비회원으로 시작하기
      if (input == 1) {
        menuList.noneMemberMenuList();
        continue Main;

        // 2. 로그인
      } else if (input == 2) {
        try {
          input = menuList.loginExecute(loginInfo);

        } catch (Exception e) {
          System.out.println();
          System.out.println("일치하는 로그인 정보가 없습니다.\n");

          menuList.findMemberInfoList();
          continue;
        }

        // 관리자 메뉴
        if (input == 2) {
          menuList.adminMenuList();
          continue Main;

          // 0. 종료하기
        } else if (input == 0)
          continue Main;
      }

      // 로그인 성공 후 메뉴
      Member : while (true) {
        input = menuList.memberMenuList(); 

        if (input == 0) {
          System.out.println("로그아웃이 완료되었습니다.\n");
          continue Main;

          // 1. 무료 스터디
        } else if (input == 1) {
          freeStudyMenuList.freeStudyMenuList();
          continue Member;

          // 2. 유료 스터디
        } else if (input == 2) {
          chargeStudyMenuList.chargeStudyMenuList();
          continue Member;

          // 3. 관심목록
        } else if (input == 3) {
          interestMenuList.interestMenuList();
          continue Member;

          // 4. 커뮤니티
        } else if (input == 4) {
          Community : while (true) {
            input = communityMenuList.communityMainMenuList();

            // 1. 질문
            if (input == 1) {
              communityMenuList.communityQaMenuList();
              continue Community;

              // 2. 정보 
            } else if (input == 2) {
              communityMenuList.communityInfoMenuList();
              continue Community;

              // 3. 스몰톡
            } else if (input == 3) {
              communityMenuList.communityTalkMenuList();
              continue Community;  

              // 0. 이전
            } else if(input == 0) {
              continue Member;

            } else {
              System.out.println("잘못된 번호입니다.\n");
              continue Community;
            }
          }

          // 3. 관심목록 메뉴
        } else if (input == 5) { // 캘린더
          // [추가]

          // 멘토 신청하기
        } else if (input == 6) {
          mentorApplicantAddHandler.execute();

        } else {
          System.out.println("잘못된 번호입니다.\n");
          continue;
        }

        // 3. 회원가입
      } else if (input == 3) {
        newMemberHandler.add(loginInfo);
        continue;

        // 4. 아이디 / 비밀번호 찾기
      } else if (input == 4) {
        menuList.findMemberInfoList();
        return;

        // 0. 종료하기
      } else if (input == 0) {
        System.out.println("종료되었습니다.\n");
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
      }
    }
  }
