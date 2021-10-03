package com.studywithus.handler.study;

import java.util.Collection;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;

public class ChargeStudyDeleteRequestListHandler implements Command {

  RequestAgent requestAgent;

  public ChargeStudyDeleteRequestListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  // 관리자 관점
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[스터디 삭제 요청 내역 / 조회]\n");

    requestAgent.request("chargeStudy.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("유료 스터디 게시글이 존재하지 않습니다.");
      return;
    }

    Collection<Study> studyList = requestAgent.getObjects(Study.class);

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
