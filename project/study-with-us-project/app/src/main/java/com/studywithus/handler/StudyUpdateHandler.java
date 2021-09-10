package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler2.AuthLoginHandler;
import com.studywithus.util.Prompt;

public class StudyUpdateHandler extends AbstractStudyHandler {

  public StudyUpdateHandler(List<Study> freeStudyList, List<Study> chargeStudyList) {
    super(freeStudyList, chargeStudyList);
  }

  // 무료 스터디 수정
  public void execute() {
    System.out.println("[무료 스터디 / 수정]\n");

    int no = Prompt.inputInt("번호? ");
    System.out.println();

    Study Study = findByNo(no);

    if (Study == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    if (Study.getWriter().getNo() != AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("변경 권한이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", Study.getTitle()));
    String explanation = Prompt.inputString(String.format("[%s] 수정할 설명: ", Study.getExplanation()));
    String rule = Prompt.inputString(String.format("[%s] 수정할 규칙: ", Study.getRule()));

    System.out.println();

    String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("무료 스터디 수정을 취소하였습니다.\n");
      return;
    }

    Study.setTitle(title);
    Study.setExplanation(explanation);
    Study.setRule(rule);

    System.out.println();
    System.out.println("무료 스터디를 수정하였습니다.\n");

    // 유료 스터디 수정
    System.out.println("[유료 스터디 / 수정]");
    int no = Prompt.inputInt("번호? ");

    Study study = findByNo(no);

    if (study == null) {
      System.out.println();
      System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 유료 스터디 / 수정된 스터디 제목: ", study.getTitle()));
    String explanation = Prompt.inputString(String.format("[%s] 유료 스터디 / 수정된 내용: ", study.getExplanation()));

    String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("유료 스터디 수정을 취소하였습니다.\n");
      return;
    }

    study.setTitle(title);
    study.setExplanation(explanation);
    System.out.println();
    System.out.println("유료 스터디를 수정하였습니다.\n");
  }
}
