package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public class ChargeStudyListHandler extends AbstractStudyHandler {

  public ChargeStudyListHandler(List<Study> chargeStudyList) {
    super(chargeStudyList);	
  }

  @Override
  public void execute() {
    System.out.println("[유료 스터디 / 조회]\n");

    for (Study chargeStudy : studyList) {
      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", chargeStudy.getNo(), chargeStudy.getTitle(),
          chargeStudy.getWriter().getName(), chargeStudy.getRegisteredDate(), chargeStudy.getViewCount(),
          chargeStudy.getLike());
    }
  }
}
