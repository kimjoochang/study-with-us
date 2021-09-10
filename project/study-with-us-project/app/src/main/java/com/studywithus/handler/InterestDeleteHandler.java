package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class InterestDeleteHandler extends AbstractInterestHandler {

  public InterestDeleteHandler(List<Study> freeInterestList, List<Study> chargeInterestList) {
    super(freeInterestList, chargeInterestList);
  }

  // 무료 스터디 관심목록 삭제
  @Override
  public void execute() {
    System.out.println("[메인 / 관심 목록 / 삭제]\n");

    for (Study Study : freeInterestList) {
      System.out.printf("%d, %s, %s, %s, %d, %d\n", Study.getNo(), Study.getTitle(),
          Study.getWriter(), Study.getRegisteredDate(),
          Study.getViewCount(), Study.getLike());
    }

    System.out.println();

    int no = Prompt.inputInt("번호? ");

    Study Study = findByNo(no);

    if (Study == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디 관심 목록이 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
      return;
    }

    freeInterestList.remove(Study);

    System.out.println();
    System.out.println("무료 스터디 관심 목록을 삭제하였습니다.\n");

    // 유료 스터디 관심 목록 삭제
    System.out.println("[유료 스터디 / 관심 목록 / 삭제]\n");

    for (Study study : chargeInterestList) {
      System.out.println();
      System.out.printf("%d, %s, %s, %d \n",
          study.getNo(),
          study.getTitle(),
          study.getWriter(),
          study.getPrice());
    }
    System.out.println();

    int no = Prompt.inputInt("번호? ");

    Study study = findByNo(no);

    if (study == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디 관심 목록이 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
      return;
    }

    chargeInterestList.remove(study);

    System.out.println();
    System.out.println("무료 스터디 관심 목록을 삭제하였습니다.\n");
  }
}
