package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudyDetailHandler extends AbstractFreeStudyHandler {

  public FreeStudyDetailHandler(List<FreeStudy> freeStudyList) {
    super(freeStudyList);
  }

  // 무료 스터디 상세보기
  public void execute() {
    System.out.println("[무료 스터디 / 상세보기]");

    int no = Prompt.inputInt("번호? ");
    FreeStudy study = findByNo(no);

    if (study == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    System.out.printf("제목: %s\n", study.getTitle());
    System.out.printf("내용: %s\n", study.getExplanation());
    System.out.printf("팀장: %s\n", study.getWriter());

    if (study.getArea().length() != 0) {
      System.out.printf("지역: %s\n", study.getArea());
    }

    System.out.printf("설명: %s\n", study.getExplanation());
    System.out.printf("규칙: %s\n", study.getRule());
    System.out.printf("등록일: %s\n", study.getRegisteredDate());

    study.setViewCount(study.getViewCount() + 1);
    System.out.printf("조회수: %d\n", study.getViewCount());
  }
}
