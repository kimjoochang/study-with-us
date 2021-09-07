package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;

public class FreeStudyListHandler extends AbstractFreeStudyHandler {

  FreeStudy freeStudy;

  public FreeStudyListHandler(List<FreeStudy> freeStudyList) {
    super(freeStudyList);
  }

  // 무료 스터디 조회
  @Override
  public void execute() {
    System.out.println("[무료 스터디 / 조회]\n");

    if (freeStudy == null) {
      System.out.println("생성된 무료 스터디가 없습니다.");
      return;
    }

    for (FreeStudy freeStudy : freeStudyList) {
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
