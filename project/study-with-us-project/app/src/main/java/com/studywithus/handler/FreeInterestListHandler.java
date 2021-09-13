package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public class FreeInterestListHandler extends AbstractFreeInterestHandler {

  public FreeInterestListHandler(List<Study> freeInterestList) {
    super(freeInterestList);
  }

  @Override
  public void execute() {
    System.out.println("[무료 스터디 관심목록 / 조회]");

    for (Study freeStudy : freeInterestList) {
      System.out.println();
      System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", freeStudy.getNo(), freeStudy.getTitle(),
          freeStudy.getWriter().getName(), freeStudy.getRegisteredDate(),
          freeStudy.getViewCount(), freeStudy.getLike());
    }
  }
}
