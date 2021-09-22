package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class FreeStudyApplyDetailHandler extends AbstractStudyHandler {

  List<Study> freeStudyApplyList;

  public FreeStudyApplyDetailHandler(List<Study> freeStudyList, List<Study> freeStudyApplyList) {
    super(freeStudyList);
    this.freeStudyApplyList = freeStudyApplyList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 신청 내역 / 상세보기] \n");
    System.out.println("--------------------------------");

    for(Study freeStudy : freeStudyApplyList) {
      System.out.printf("%s, %s\n",freeStudy.getNo(), freeStudy.getTitle());
      System.out.println("------------------------------");
    }

    int no = Prompt.inputInt("번호: ");
    Study freeStudy = findByNo(no);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("입력하신 이름과 일치하는 신청 내역이 없습니다.\n");
      return;
    }

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
    System.out.printf("모집인원 = %d / %d\n", freeStudy.getMembers().size(), freeStudy.getMaxMembers());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());
    System.out.printf("좋아요수: %d\n", freeStudy.getLikeMembers().size());
    System.out.println();
  }
}
