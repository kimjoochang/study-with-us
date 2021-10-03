package com.studywithus.handler.study;

import java.util.Collection;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;

public class FreeStudyApplyListHandler implements Command {

  // List<Study> freeStudyApplyList;
  RequestAgent requestAgent;

  public FreeStudyApplyListHandler(RequestAgent requestAgent) {
    // super(freeStudyList);
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 신청 내역 / 조회] \n");

    requestAgent.request("freeStudy.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("무료 스터디 조회 실패!");
      return;
    }

    Collection<Study> studyList = requestAgent.getObjects(Study.class);
    int type = 0;

    for (Study freeStudy : studyList) {
      for (Member member : freeStudy.getApplicants()) {
        if (member.getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
          type = 1;
          System.out.printf("제목: %s\n", freeStudy.getTitle());
          System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());

          if (freeStudy.getArea() != null) {
            System.out.printf("온/오프라인: %s\n", freeStudy.getOFFLINE());
            System.out.printf("지역: %s\n", freeStudy.getArea());
          } else {
            System.out.printf("온/오프라인: %s\n", freeStudy.getONLINE());
          }
          System.out.printf("설명: %s\n", freeStudy.getContent());
          System.out.printf("규칙: %s\n", freeStudy.getRule());
          System.out.printf("모집인원 = %d / %d\n", freeStudy.getMembers().size(),
              freeStudy.getMaxMembers());
          System.out.printf("시작일: %s\n", freeStudy.getStartDate());
          System.out.printf("종료일: %s\n", freeStudy.getEndDate());
          System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

          freeStudy.setViewCount(freeStudy.getViewCount() + 1);
          System.out.printf("조회수: %d\n", freeStudy.getViewCount());
          System.out.printf("좋아요수: %d\n", freeStudy.getLikeMembers().size());
        } else {
          if (type == 1) {
            continue;
          } else {
            type = 0;
          }
        }
      }
    }
    if (type == 0) {
      System.out.println("무료 스터디 신청 내역이 없습니다.");
    }
  }
}
