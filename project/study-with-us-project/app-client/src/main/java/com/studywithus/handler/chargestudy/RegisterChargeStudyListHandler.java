package com.studywithus.handler.chargestudy;

import java.util.Collection;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;

public class RegisterChargeStudyListHandler implements Command {

  RequestAgent requestAgent;

  public RegisterChargeStudyListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 내가 생성한 유료 스터디]\n");

    requestAgent.request("chargeStudy.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("유료 스터디 게시글이 존재하지 않습니다.");
      return;
    }

    Collection<Study> chargeStudyList = requestAgent.getObjects(Study.class);

    for (Study chargeStudy : chargeStudyList) {

      if (chargeStudy.getWriter().getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
        System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 모집인원 = %d / %d, 조회수 = %d, 좋아요 = %d]\n",
            chargeStudy.getNo(),
            chargeStudy.getTitle(),
            chargeStudy.getWriter().getName(),
            chargeStudy.getRegisteredDate(),
            chargeStudy.getMembers().size(),
            chargeStudy.getMaxMembers(),
            chargeStudy.getViewCount(),
            chargeStudy.getLikeMembers().size());
      }
    }
    System.out.println();
  }
}
