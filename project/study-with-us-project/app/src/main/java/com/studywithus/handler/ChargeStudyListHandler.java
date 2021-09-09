package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public class ChargeStudyListHandler extends AbstractChargeStudyHandler{

  public ChargeStudyListHandler(List<Study> chargeStudyList) {
    super(chargeStudyList);	
  }

  // 유료 스터디 조회
  @Override
  public void execute() {
    System.out.println("[유료 스터디 / 조회]\n");

    for (Study study : chargeStudyList) {
      System.out.printf("%d, %s, %s, %s, %d, %d\n", study.getNo(), study.getTitle(),
          study.getWriter(), study.getRegisteredDate(), study.getViewCount(),
          study.getLike());
    }
  }
}
