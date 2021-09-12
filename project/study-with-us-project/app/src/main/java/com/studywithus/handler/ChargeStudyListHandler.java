package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public class ChargeStudyListHandler extends AbstractChargeStudyHandler{

  public ChargeStudyListHandler(List<Study> chargeStudyList) {
    super(chargeStudyList);	
  }

  @Override
  public void execute() {
    System.out.println("[유료 스터디 / 조회]\n");

    for (Study chargeStudy : chargeStudyList) {
      System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", chargeStudy.getNo(), chargeStudy.getTitle(),
          chargeStudy.getWriter(), chargeStudy.getRegisteredDate(), chargeStudy.getViewCount(),
          chargeStudy.getLike());
    }
  }
}
