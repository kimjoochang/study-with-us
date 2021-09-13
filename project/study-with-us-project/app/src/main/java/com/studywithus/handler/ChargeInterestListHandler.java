package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public class ChargeInterestListHandler extends AbstractChargeInterestHandler {

  public ChargeInterestListHandler(List<Study> chargeInterestList) {
    super(chargeInterestList);
  }

  @Override
  public void execute() {
    System.out.println("[유료 스터디 관심목록 / 조회]");

    for (Study chargeInterest : chargeInterestList) {
      System.out.println();
      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", chargeInterest.getNo(), chargeInterest.getTitle(),
          chargeInterest.getWriter().getName(), chargeInterest.getRegisteredDate(),
          chargeInterest.getViewCount(), chargeInterest.getLike());
    }
  }
}
