package com.studywithus.menuList;

import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.handler.CommunityInfoAddHandler;
import com.studywithus.handler.CommunityInfoDeleteHandler;
import com.studywithus.handler.CommunityInfoDetailHandler;
import com.studywithus.handler.CommunityInfoListHandler;
import com.studywithus.handler.CommunityInfoUpdateHandler;
import com.studywithus.handler.CommunityQaAddHandler;
import com.studywithus.handler.CommunityQaDeleteHandler;
import com.studywithus.handler.CommunityQaDetailHandler;
import com.studywithus.handler.CommunityQaListHandler;
import com.studywithus.handler.CommunityQaUpdateHandler;
import com.studywithus.handler.CommunityTalkAddHandler;
import com.studywithus.handler.CommunityTalkDeleteHandler;
import com.studywithus.handler.CommunityTalkDetailHandler;
import com.studywithus.handler.CommunityTalkListHandler;
import com.studywithus.handler.CommunityTalkUpdateHandler;
import com.studywithus.util.Prompt;

public class CommunityMenuList {

  public List <Community> communityList;

  CommunityInfoAddHandler communityInfoAddHandler = new CommunityInfoAddHandler(communityList);
  CommunityInfoListHandler communityInfoListHandler = new CommunityInfoListHandler(communityList);
  CommunityInfoDetailHandler communityInfoDetailHandler = new CommunityInfoDetailHandler(communityList);
  CommunityInfoDeleteHandler communityInfoDeleteHandler = new CommunityInfoDeleteHandler(communityList);
  CommunityInfoUpdateHandler communityInfoUpdateHandler = new CommunityInfoUpdateHandler(communityList);

  CommunityQaAddHandler communityQaAddHandler = new CommunityQaAddHandler(communityList);
  CommunityQaListHandler communityQaListHandler = new CommunityQaListHandler(communityList);
  CommunityQaDetailHandler communityQaDetailHandler = new CommunityQaDetailHandler(communityList);
  CommunityQaDeleteHandler communityQaDeleteHandler = new CommunityQaDeleteHandler(communityList);
  CommunityQaUpdateHandler communityQaUpdateHandler = new CommunityQaUpdateHandler(communityList);

  CommunityTalkAddHandler communityTalkAddHandler = new CommunityTalkAddHandler(communityList);
  CommunityTalkListHandler communityTalkListHandler = new CommunityTalkListHandler(communityList);
  CommunityTalkDetailHandler communityTalkDetailHandler = new CommunityTalkDetailHandler(communityList);
  CommunityTalkDeleteHandler communityTalkDeleteHandler = new CommunityTalkDeleteHandler(communityList);
  CommunityTalkUpdateHandler communityTalkUpdateHandler = new CommunityTalkUpdateHandler(communityList);

  int input;

  //커뮤니티 기본 메뉴 조회
  public int communityMainMenuList() {
    System.out.println("[커뮤니티]\n");
    System.out.println("1. 질문");
    System.out.println("2. 정보");
    System.out.println("3. 스몰톡");
    System.out.println("0. 이전\n");

    input = Prompt.inputInt("게시판을 선택해주세요. > ");

    System.out.println();
    return input;
  }

  // 커뮤니티 질문 게시판 메뉴 조회
  public void communityQaMenuList() {

    while(true) {
      System.out.println("[커뮤니티 / 질문]\n");
      System.out.println("1. 생성");
      System.out.println("2. 조회");
      System.out.println("3. 상세보기");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        communityQaAddHandler.execute();

      } else if (input == 2) {
        communityQaListHandler.execute();

      } else if (input == 3) {
        communityQaDetailHandler.execute();

      } else if (input == 4) {
        communityQaUpdateHandler.execute();

      } else if (input == 5) {
        communityQaDeleteHandler.execute();

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      continue;
    }
  }

  // 커뮤니티 정보 게시판 메뉴 조회
  public void communityInfoMenuList() {

    while(true) {
      System.out.println("[커뮤니티 / 정보]\n");
      System.out.println("1. 생성");
      System.out.println("2. 조회");
      System.out.println("3. 상세보기");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        communityInfoAddHandler.execute();

      } else if (input == 2) {
        communityInfoListHandler.execute();

      } else if (input == 3) {
        communityInfoDetailHandler.execute();

      } else if (input == 4) {
        communityInfoUpdateHandler.execute();

      } else if (input == 5) {
        communityInfoDeleteHandler.execute();

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      continue;
    }
  }

  // 커뮤니티 스몰톡 게시판 메뉴 조회
  public void communityTalkMenuList() {

    while(true) {
      System.out.println("[커뮤니티 / 스몰톡]\n");
      System.out.println("1. 생성");
      System.out.println("2. 조회");
      System.out.println("3. 상세보기");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        communityTalkAddHandler.execute();

      } else if (input == 2) {
        communityTalkListHandler.execute();

      } else if (input == 3) {
        communityTalkDetailHandler.execute();

      } else if (input == 4) {
        communityTalkUpdateHandler.execute();

      } else if (input == 5) {
        communityTalkDeleteHandler.execute();

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.\n");
        continue;
      }
      continue;
    }
  }

}
