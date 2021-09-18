package com.studywithus.handler.freestudy;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.abstract.AbstractInterestHandler;

public class FreeStudyInterestListHandler extends AbstractInterestHandler {

  public FreeStudyInterestListHandler(List<Study> freeInterestList) {
    super(freeInterestList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 관심목록 / 조회]\n");

    if (interestList.isEmpty() == true) {
      System.out.println("무료 스터디 관심목록이 존재하지 않습니다.\n");
      return;
    }

    for (Study freeInterest : interestList) {
      System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]",
          freeInterest.getNo(),
          freeInterest.getTitle(),
          freeInterest.getWriter().getName(),
          freeInterest.getRegisteredDate(),
          freeInterest.getViewCount(),
          freeInterest.getLike());
    }
    System.out.println();
  }
}
