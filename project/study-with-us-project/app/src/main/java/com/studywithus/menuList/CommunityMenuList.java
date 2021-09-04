package com.studywithus.menuList;

import java.util.ArrayList;
import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.handler.CommunityAddHandler;
import com.studywithus.handler.CommunityDeleteHandler;
import com.studywithus.handler.CommunityDetailHandler;
import com.studywithus.handler.CommunityListHandler;
import com.studywithus.handler.CommunityUpdateHandler;
import com.studywithus.util.Prompt;

public class CommunityMenuList {

  private List<Community> communityInfoList = new ArrayList<>();
  private List<Community> communityQaList = new ArrayList<>();
  private List<Community> communityTalkList = new ArrayList<>();

  CommunityAddHandler communityInfoAddHandler = new CommunityAddHandler(communityInfoList);
  CommunityListHandler communityInfoListHandler = new CommunityListHandler(communityInfoList);
  CommunityDetailHandler communityInfoDetailHandler = new CommunityDetailHandler(communityInfoList);
  CommunityDeleteHandler communityInfoDeleteHandler = new CommunityDeleteHandler(communityInfoList);
  CommunityUpdateHandler communityInfoUpdateHandler = new CommunityUpdateHandler(communityInfoList);

  CommunityAddHandler communityQaAddHandler = new CommunityAddHandler(communityQaList);
  CommunityListHandler communityQaListHandler = new CommunityListHandler(communityQaList);
  CommunityDetailHandler communityQaDetailHandler = new CommunityDetailHandler(communityQaList);
  CommunityDeleteHandler communityQaDeleteHandler = new CommunityDeleteHandler(communityQaList);
  CommunityUpdateHandler communityQaUpdateHandler = new CommunityUpdateHandler(communityQaList);

  CommunityAddHandler communityTalkAddHandler = new CommunityAddHandler(communityTalkList);
  CommunityListHandler communityTalkListHandler = new CommunityListHandler(communityTalkList);
  CommunityDetailHandler communityTalkDetailHandler = new CommunityDetailHandler(communityTalkList);
  CommunityDeleteHandler communityTalkDeleteHandler = new CommunityDeleteHandler(communityTalkList);
  CommunityUpdateHandler communityTalkUpdateHandler = new CommunityUpdateHandler(communityTalkList);

  int input;

  //커뮤니티 기본 메뉴
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

  // 커뮤니티 질문, 정보, 스몰톡 게시판 공통 메뉴
  public void communityCommonMenuList() {

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


}
