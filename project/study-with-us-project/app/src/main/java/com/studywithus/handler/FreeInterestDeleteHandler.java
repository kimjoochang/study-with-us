package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeInterestDeleteHandler extends AbstractFreeInterestHandler {

  public FreeInterestDeleteHandler(List<FreeStudy> freeInterestList) {
    super(freeInterestList);
  }

  @Override
  public void execute() {
    System.out.println("[메인 / 관심 목록 / 삭제]\n");

    for (FreeStudy freeStudy : freeInterestList) {
      System.out.printf("%d, %s, %s, %s, %d, %d\n", freeStudy.getNo(), freeStudy.getTitle(),
          freeStudy.getWriter(), freeStudy.getRegisteredDate(),
          freeStudy.getViewCount(), freeStudy.getLike());
    }

    System.out.println();

    int no = Prompt.inputInt("번호? ");

    FreeStudy freeStudy = findByNo(no);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디 관심 목록이 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
      return;
    }

    freeInterestList.remove(freeStudy);

    System.out.println();
    System.out.println("무료 스터디 관심 목록을 삭제하였습니다.\n");
  }
}
