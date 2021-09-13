package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class FreeInterestDeleteHandler extends AbstractFreeInterestHandler {

  public FreeInterestDeleteHandler(List<Study> freeInterestList) {
    super(freeInterestList);
  }

  @Override
  public void execute() {
    System.out.println("[무료 스터디 관심목록 / 삭제]\n");

    for (Study freeInterest : freeInterestList) {
      System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", freeInterest.getNo(), freeInterest.getTitle(),
          freeInterest.getWriter().getName(), freeInterest.getRegisteredDate(),
          freeInterest.getViewCount(), freeInterest.getLike());
    }

    System.out.println();

    int no = Prompt.inputInt("번호: ");

    Study freeInterest = findByNo(no);

    if (freeInterest == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디 관심 목록이 없습니다.\n");

      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
      return;
    }

    freeInterestList.remove(freeInterest);

    System.out.println();
    System.out.println("무료 스터디 관심 목록을 삭제하였습니다.\n");
  }
}
