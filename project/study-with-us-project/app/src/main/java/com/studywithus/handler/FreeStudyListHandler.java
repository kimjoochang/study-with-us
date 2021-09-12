package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public class FreeStudyListHandler extends AbstractFreeStudyHandler {

  public FreeStudyListHandler(List<Study> freeStudyList) {
    super(freeStudyList);
  }

  @Override
  public void execute() {
    System.out.println("[무료 스터디 / 조회]\n");

    for (Study freeStudy : freeStudyList) {
      System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
          freeStudy.getNo(), 
          freeStudy.getTitle(), 
          freeStudy.getWriter().getName(),
          freeStudy.getRegisteredDate(),
          freeStudy.getViewCount(), 
          freeStudy.getLike());
    }
  }
}
