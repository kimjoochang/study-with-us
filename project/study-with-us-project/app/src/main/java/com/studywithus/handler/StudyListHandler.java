package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public class StudyListHandler extends AbstractStudyHandler {

  public StudyListHandler(List<Study> freeStudyList, List<Study> chargeStudyList) {
    super(freeStudyList, chargeStudyList);
  }

  @Override
  public void execute() {

    // 무료 스터디 조회
    if (Study.value) {
      System.out.println("[무료 스터디 / 조회]\n");

      for (Study Study : freeStudyList) {
        System.out.printf("%d, %s, %s, %s, %d, %d\n", 
            Study.getNo(), 
            Study.getTitle(), 
            Study.getWriter().getName(),
            Study.getRegisteredDate(),
            Study.getViewCount(), 
            Study.getLike());
      }
    }

    // 유료 스터디 조회
    else {
      System.out.println("[유료 스터디 / 조회]\n");

      for (Study study : chargeStudyList) {
        System.out.printf("%d, %s, %s, %s, %d, %d\n", study.getNo(), study.getTitle(),
            study.getWriter(), study.getRegisteredDate(), study.getViewCount(),
            study.getLike());
      }
    }
  }
}
