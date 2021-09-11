package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ChargeStudy;

public class ChargeInterestListHandler extends AbstractChargeInterestHandler {

  public ChargeInterestListHandler(List<ChargeStudy> chargeInterestList) {
    super(chargeInterestList);
  }

  @Override
  public void execute() {
    System.out.println("[메인 / 관심 목록 / 조회]");

    for (ChargeStudy chargeStudy : chargeInterestList) {
      System.out.println();
      System.out.printf("%d, %s, %s, %d \n",
          chargeStudy.getNo(),
          chargeStudy.getTitle(),
          chargeStudy.getWriter(),
          chargeStudy.getPrice());
    }
  }
}
