package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;

public class FreeStudyListHandler extends AbstractStudyHandler {

  public FreeStudyListHandler(List<Study> freeStudyList) {
    super(freeStudyList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 / 조회]\n");
    System.out.println();

    if (studyList.isEmpty() == true) {
      System.out.println("무료 스터디 게시글이 존재하지 않습니다.\n");
      return;
    }

    for (Study freeStudy : studyList) {
      if (freeStudy.getOnOffLine() == 1) {
        System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
            freeStudy.getNo(), 
            freeStudy.getTitle(), 
            freeStudy.getWriter().getName(),
            freeStudy.getONLINE(),
            freeStudy.getRegisteredDate(),
            freeStudy.getViewCount(), 
            freeStudy.getLike());
      } else {
        System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 지역 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
            freeStudy.getNo(), 
            freeStudy.getTitle(), 
            freeStudy.getWriter().getName(),
            freeStudy.getOFFLINE() ,
            freeStudy.getArea(),
            freeStudy.getRegisteredDate(),
            freeStudy.getViewCount(), 
            freeStudy.getLike());
      }
    }
    System.out.println();
  }
}
