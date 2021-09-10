package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class StudySearchHandler extends AbstractStudyHandler {

  public StudySearchHandler(List<Study> freeStudyList, List<Study> chargeStudyList) {
    super(freeStudyList, chargeStudyList);
  }

  @Override
  public void execute() {

    // 무료 스터디 검색
    if (Study.value) {
      System.out.println("[무료 스터디 / 검색]");

      String input = Prompt.inputString("검색어? ");

      for (Study Study : freeStudyList) {
        if (!Study.getTitle().contains(input) &&
            !Study.getExplanation().contains(input) &&
            !Study.getWriter().getName().contains(input)) {
          System.out.println("입력하신 검색어가 포함된 게시물이 없습니다.");
          continue;
        }

        System.out.printf("%d, %s, %s, %s, %d, %d\n", 
            Study.getNo(), 
            Study.getTitle(), 
            Study.getWriter().getName(),
            Study.getRegisteredDate(),
            Study.getViewCount(), 
            Study.getLike());
      }
    }

    // 유료 스터디 검색
    else {
      System.out.println("[유료스터디 / 검색]");

      String input = Prompt.inputString("검색어? ");
      System.out.println();

      for (Study study : chargeStudyList) {
        if (!study.getTitle().contains(input) &&
            !study.getExplanation().contains(input) &&
            !study.getWriter().getName().contains(input)) {
          System.out.println("입력하신 검색어가 포함된 게시물이 없습니다.");
          continue;
        }

        System.out.printf("%d, %s, %s, %s, %d, %d\n", 
            study.getNo(), 
            study.getTitle(), 
            study.getWriter(),
            study.getRegisteredDate(),
            study.getViewCount(), 
            study.getLike());
      }
    }
  }
}
