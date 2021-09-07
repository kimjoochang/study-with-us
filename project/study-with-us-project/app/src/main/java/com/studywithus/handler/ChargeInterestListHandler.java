package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ChargeStudy;

public class ChargeInterestListHandler extends AbstractChargeInterestHandler {

  ChargeStudy chargeStudy;

  public ChargeInterestListHandler(List<ChargeStudy> chargeInterestList) {
    super(chargeInterestList);
  }

  @Override
  public void execute() {
    System.out.println("[유료 스터디 / 관심 목록 / 조회]");

    if (chargeStudy == null) {
      System.out.println("생성된 유료 스터디 관심 목록이 없습니다.");
      return;
    }

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
