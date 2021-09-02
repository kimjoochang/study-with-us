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
    System.out.println("[무료 스터디 / 수정]");

    int no = Prompt.inputInt("번호? ");

    FreeStudy study = findByNo(no);

    if (study == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", study.getTitle()));
    String explanation = Prompt.inputString(String.format("[%s] 수정할 설명: ", study.getExplanation()));
    String rule = Prompt.inputString(String.format("[%s] 수정할 규칙: ", study.getRule()));

    String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("무료 스터디 수정을 취소하였습니다.\n");
      return;
    }

    study.setTitle(title);
    study.setExplanation(explanation);
    study.setRule(rule);

    System.out.println();
    System.out.println("무료 스터디를 수정하였습니다.\n");
  }
}
