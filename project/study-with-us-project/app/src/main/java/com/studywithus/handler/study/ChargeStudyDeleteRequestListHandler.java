package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;

public class ChargeStudyDeleteRequestListHandler extends AbstractStudyHandler {

  public ChargeStudyDeleteRequestListHandler( List<Study> chargeStudyList) {
    super(chargeStudyList);
  }

  // 관리자 관점
  @Override
  public void execute(CommandRequest request) {
    System.out.println("[스터디 삭제 요청 내역 / 조회]\n");

    for(Study chargeStudy : studyList) {
      if (chargeStudy.isDeleteRequest()) {
        System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 모집인원 = %d / %d]\n",
            chargeStudy.getNo(),
            chargeStudy.getTitle(),
            chargeStudy.getWriter().getName(),
            chargeStudy.getRegisteredDate(),
            chargeStudy.getMembers().size(),
            chargeStudy.getMaxMembers());
      }
    }
  }
}
