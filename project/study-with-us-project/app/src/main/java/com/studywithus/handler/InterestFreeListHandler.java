package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;

public class InterestFreeListHandler extends AbstractInterestFreeHandler {

  public InterestFreeListHandler(List<FreeStudy> freeInterestList) {
    super(freeInterestList);
  }
  @Override
  public void execute() {
    System.out.println("[무료 스터디 / 관심 목록 / 조회]");

    for (FreeStudy freeStudy : freeInterestList) {
      if(freeStudy.getOnOffLine().equals("2")) {
        System.out.println();

        System.out.printf("%d, %s, %s, %d, %s\n",
            freeStudy.getNo(),
            freeStudy.getTitle(),
            freeStudy.getWriter(),
            freeStudy.getOnOffLine(),
            freeStudy.getArea());

      } else {
        System.out.println();

        System.out.printf("%d, %s, %s, %d \n",
            freeStudy.getNo(),
            freeStudy.getTitle(),
            freeStudy.getWriter(),
            freeStudy.getOnOffLine());
      }
    }
  }
}
