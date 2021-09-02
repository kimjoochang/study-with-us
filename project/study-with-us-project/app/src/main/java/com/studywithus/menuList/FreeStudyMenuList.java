package com.studywithus.menuList;

import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.handler.FreeStudyAddHandler;
import com.studywithus.handler.FreeStudyApplyHandler;
import com.studywithus.handler.FreeStudyDeleteHandler;
import com.studywithus.handler.FreeStudyDetailHandler;
import com.studywithus.handler.FreeStudyListHandler;
import com.studywithus.handler.FreeStudyUpdateHandler;
import com.studywithus.util.Prompt;

public class FreeStudyMenuList {

  private List<FreeStudy> freeStudyList;

  FreeStudyApplyHandler freeStudyApplyHandler = new FreeStudyApplyHandler(freeStudyList);
  FreeStudyAddHandler freeStudyAddHandler = new FreeStudyAddHandler(freeStudyList);
  FreeStudyListHandler freeStudyListHandler = new FreeStudyListHandler(freeStudyList);
  FreeStudyDetailHandler freeStudyDetailHandler = new FreeStudyDetailHandler(freeStudyList);
  FreeStudyUpdateHandler freeStudyUpdateHandler = new FreeStudyUpdateHandler(freeStudyList);
  FreeStudyDeleteHandler freeStudyDeleteHandler = new FreeStudyDeleteHandler(freeStudyList);

  int input;

  // 무료 스터디 메뉴 조회
  public void freeStudyMenuList() {

    while(true) {
      System.out.println("[무료 스터디]\n");
      System.out.println("1. 생성");
      System.out.println("2. 조회");
      System.out.println("3. 상세보기");
      System.out.println("4. 수정");
      System.out.println("5. 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");

      // 1. 생성
      if (input == 1) {
        freeStudyAddHandler.execute();

        // 2. 조회
      } else if (input == 2) {
        freeStudyListHandler.execute();

        // 3. 상세보기
      } else if (input == 3) {
        freeStudyDetailHandler.execute();
        freeStudyApplyHandler.execute();

        // 4. 수정
      } else if (input == 4) {
        freeStudyUpdateHandler.execute();

        // 5. 삭제
      } else if (input == 5) {
        freeStudyDeleteHandler.execute();

        // 0. 이전
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
