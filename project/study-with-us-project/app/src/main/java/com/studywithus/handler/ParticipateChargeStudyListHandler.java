package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public class ParticipateChargeStudyListHandler implements Command {

  List<Study> participateChargeStudyList;

  public ParticipateChargeStudyListHandler(List<Study> participateChargeStudyList) {
    this.participateChargeStudyList = participateChargeStudyList;
  }

  @Override
  public void execute() {
    System.out.println("[마이 페이지 / 나의 활동 / 내 스터디 / 참여 유료 스터디]");

    for (Study chargeStudy : participateChargeStudyList) {
      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", chargeStudy.getNo(), chargeStudy.getTitle(),
          chargeStudy.getWriter().getName(), chargeStudy.getRegisteredDate(), chargeStudy.getViewCount(),
          chargeStudy.getLike());
    }
  }
}
