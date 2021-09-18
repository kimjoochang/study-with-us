package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;

public class ChargeStudyDeletedListHandler extends AbstractStudyHandler {

  public ChargeStudyDeletedListHandler( List<Study> chargeDetailRequestList) {
    super(chargeDetailRequestList);
  }

  // 관리자 관점
  @Override
  public void execute(CommandRequest request) {
    System.out.println("[스터디 삭제 요청 내역 / 조회]\n");
    System.out.println("-----------------------------");

    for(Study study : studyList) {
      System.out.println("스터디 제목: " + study.getTitle());
      System.out.println("멘토: " + study.getWriter().getName());
      System.out.println("---------------------------");
    }
  }
}
