package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class StudyApplyDetailHandler extends AbstractStudyHandler{

  public StudyApplyDetailHandler(List<Study> StudyApplyList) {
    super(StudyApplyList, 1);
  }

  @Override
  public void execute() {
    System.out.println("[무료 스터디 신청 내역 / 상세보기] \n");
    System.out.println("--------------------------------");

    for(Study Study : StudyApplyList) {
      System.out.printf("%s, %s\n",Study.getNo(), Study.getTitle());
      System.out.println("--------------------------------");
    }

    int no = Prompt.inputInt("번호? ");
    Study Study = findByNo(no);

    if (Study == null) {
      System.out.println();
      System.out.println("입력하신 이름과 일치하는 신청 내역이 없습니다.\n");
      return;
    }

    System.out.println();
    System.out.printf("제목: %s\n", Study.getTitle());
    System.out.printf("팀장: %s\n", Study.getWriter().getName());

    if (Study.getArea() != null) {
      System.out.printf("지역: %s\n", Study.getArea());
    }

    System.out.printf("설명: %s\n", Study.getExplanation());
    System.out.printf("규칙: %s\n", Study.getRule());
    System.out.printf("등록일: %s\n", Study.getRegisteredDate());
  }
}
