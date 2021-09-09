package com.studywithus.handler.copy;

import java.util.List;
import com.studywithus.domain.Study;

public class ChargeInterestListHandler extends AbstractChargeInterestHandler {

  public ChargeInterestListHandler(List<Study> chargeInterestList) {
    super(chargeInterestList);
  }

  @Override
  public void execute() {
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
