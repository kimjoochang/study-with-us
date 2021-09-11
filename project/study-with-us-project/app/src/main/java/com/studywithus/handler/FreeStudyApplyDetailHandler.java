package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudyApplyDetailHandler extends AbstractFreeStudyHandler{

  public FreeStudyApplyDetailHandler(List<FreeStudy> freeStudyApplyList) {
    super(freeStudyApplyList, 1);
  }

  @Override
  public void execute() {
    System.out.println("[무료 스터디 신청 내역 / 상세보기] \n");
    System.out.println("--------------------------------");
    for(FreeStudy freeStudy : freeStudyApplyList) {
      System.out.printf("%s, %s\n",freeStudy.getNo(), freeStudy.getTitle());
      System.out.println("--------------------------------");
    }

    int no = Prompt.inputInt("번호? ");
    FreeStudy freeStudy = findByNo(no);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("입력하신 이름과 일치하는 신청 내역이 없습니다.\n");
      return;
    }

    System.out.println();
    System.out.printf("제목: %s\n", freeStudy.getTitle());
    System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());

    if (freeStudy.getArea() != null) {
      System.out.printf("지역: %s\n", freeStudy.getArea());
    }

    System.out.printf("설명: %s\n", freeStudy.getExplanation());
    System.out.printf("규칙: %s\n", freeStudy.getRule());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());
  }


}
