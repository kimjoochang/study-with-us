package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class ChargeInterestDeleteHandler extends AbstractChargeInterestHandler {

  public ChargeInterestDeleteHandler(List<Study> chargeInterestList) {
    super(chargeInterestList);
  }

  @Override
  public void execute() {
    System.out.println("[유료 스터디 관심목록 / 삭제]\n");

    for (Study chargeInterest : chargeInterestList) {
      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", chargeInterest.getNo(), chargeInterest.getTitle(),
          chargeInterest.getWriter().getName(), chargeInterest.getRegisteredDate(),
          chargeInterest.getViewCount(), chargeInterest.getLike());
    }

    System.out.println();

    int no = Prompt.inputInt("번호: ");

    Study chargeInterest = findByNo(no);

    if (chargeInterest == null) {
      System.out.println();
      System.out.println("해당 번호의 유료 스터디 관심 목록이 없습니다.\n");

      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("유료 스터디 관심 목록을 취소하였습니다.\n");
      return;
    }

    chargeInterestList.remove(chargeInterest);

    System.out.println();
    System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n");
  }
}
