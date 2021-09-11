package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudyUpdateHandler extends AbstractFreeStudyHandler {

  public FreeStudyUpdateHandler(List<FreeStudy> freeStudyList) {
    super(freeStudyList);
  }

  // 무료 스터디 수정
  public void execute() {
    System.out.println("[무료 스터디 / 수정]\n");

    int no = Prompt.inputInt("번호? ");
    System.out.println();

    FreeStudy freeStudy = findByNo(no);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    if (freeStudy.getWriter().getNo() != AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("변경 권한이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", freeStudy.getTitle()));
    String explanation = Prompt.inputString(String.format("[%s] 수정할 설명: ", freeStudy.getExplanation()));
    String rule = Prompt.inputString(String.format("[%s] 수정할 규칙: ", freeStudy.getRule()));

    System.out.println();

    String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("무료 스터디 수정을 취소하였습니다.\n");
      return;
    }

    freeStudy.setTitle(title);
    freeStudy.setExplanation(explanation);
    freeStudy.setRule(rule);

    System.out.println();
    System.out.println("무료 스터디를 수정하였습니다.\n");
  }
}
