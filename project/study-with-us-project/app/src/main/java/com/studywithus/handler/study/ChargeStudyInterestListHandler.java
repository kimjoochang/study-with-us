package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;

public class ChargeStudyInterestListHandler extends AbstractInterestHandler {

  public ChargeStudyInterestListHandler(List<Study> chargeInterestList) {
    super(chargeInterestList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[유료 스터디 관심목록 / 조회]\n");

    if (interestList.isEmpty() == true) {
      System.out.println("유료 스터디 게시글이 존재하지 않습니다.\n");
      return;
    }

    for (Study chargeInterest : interestList) {
      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]",
          chargeInterest.getNo(),
          chargeInterest.getTitle(),
          chargeInterest.getWriter().getName(),
          chargeInterest.getRegisteredDate(),
          chargeInterest.getViewCount(),
          chargeInterest.getLike());
    }
    System.out.println();
  }
}
