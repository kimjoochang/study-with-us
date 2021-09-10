package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public class InterestListHandler extends AbstractInterestHandler {

  public InterestListHandler(List<Study> freeInterestList, List<Study> chargeInterestList) {
    super(freeInterestList, chargeInterestList);
  }

  // 무료 스터디 관심목록 조회
  @Override
  public void execute() {
    System.out.println("[메인 / 관심 목록 / 조회]");

    for (Study Study : freeInterestList) {
      System.out.println();
      System.out.printf("%d, %s, %s, %s, %d, %d\n", Study.getNo(), Study.getTitle(),
          Study.getWriter(), Study.getRegisteredDate(),
          Study.getViewCount(), Study.getLike());
    }

    System.out.println("[유료 스터디 / 관심 목록 / 조회]");

    for (Study study : chargeInterestList) {
      System.out.println();
      System.out.printf("%d, %s, %s, %d \n",
          study.getNo(),
          study.getTitle(),
          study.getWriter(),
          study.getPrice());
    }
  }
}
