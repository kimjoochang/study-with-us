package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;

public class FreeInterestListHandler extends AbstractFreeInterestHandler {

  public FreeInterestListHandler(List<FreeStudy> freeInterestList) {
    super(freeInterestList);
  }

  @Override
  public void execute() {
    System.out.println("[메인 / 관심 목록 / 조회]");

    for (FreeStudy freeStudy : freeInterestList) {
      System.out.println();
      System.out.printf("%d, %s, %s, %s, %d, %d\n", freeStudy.getNo(), freeStudy.getTitle(),
          freeStudy.getWriter(), freeStudy.getRegisteredDate(),
          freeStudy.getViewCount(), freeStudy.getLike());
    }
  }
}
