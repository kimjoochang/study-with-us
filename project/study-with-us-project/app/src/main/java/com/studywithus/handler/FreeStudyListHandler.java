package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public class FreeStudyListHandler extends AbstractStudyHandler {

  public FreeStudyListHandler(List<Study> freeStudyList) {
    super(freeStudyList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 / 조회]\n");

    if (studyList != null) {

      for (Study freeStudy : studyList) {
        System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
            freeStudy.getNo(), 
            freeStudy.getTitle(), 
            freeStudy.getWriter().getName(),
            freeStudy.getRegisteredDate(),
            freeStudy.getViewCount(), 
            freeStudy.getLike());
        System.out.println();

      }
    } else {
      System.out.println();
      System.out.println("무료 스터디 게시글이 존재하지 않습니다.\n");
      return;
    }
  }
}

