package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudySearchHandler extends AbstractFreeStudyHandler {

  public FreeStudySearchHandler(List<FreeStudy> freeStudyList) {
    super(freeStudyList);
  }

  @Override
  public void execute() {
    System.out.println("[무료 스터디 / 검색]");

    String input = Prompt.inputString("검색어? ");

    for (FreeStudy freeStudy : freeStudyList) {
      if (!freeStudy.getTitle().contains(input) &&
          !freeStudy.getExplanation().contains(input) &&
          !freeStudy.getWriter().getName().contains(input)) {
        System.out.println("입력하신 검색어가 포함된 게시물이 없습니다.");
        continue;
      }

      System.out.printf("%d, %s, %s, %s, %d, %d\n", 
          freeStudy.getNo(), 
          freeStudy.getTitle(), 
          freeStudy.getWriter().getName(),
          freeStudy.getRegisteredDate(),
          freeStudy.getViewCount(), 
          freeStudy.getLike());
    }
  }
}
