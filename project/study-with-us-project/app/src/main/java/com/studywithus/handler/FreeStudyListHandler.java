package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;

public class FreeStudyListHandler extends AbstractFreeStudyHandler {

  public FreeStudyListHandler(List<FreeStudy> freeStudyList) {
    super(freeStudyList);
  }

  // 무료 스터디 조회
  @Override
  public void execute() {
    System.out.println("[무료 스터디 / 조회]\n");

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
