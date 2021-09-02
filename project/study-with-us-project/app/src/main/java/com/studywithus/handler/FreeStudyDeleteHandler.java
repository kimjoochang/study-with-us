package com.studywithus.handler;

import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudyDeleteHandler extends AbstractFreeStudyHandler {

  static final int MAX_LENGTH = 5;

  FreeStudy[] studies = new FreeStudy[MAX_LENGTH];
  int size = 0;

  // 무료 스터디 삭제
  public void execute() {
    System.out.println("[무료 스터디 삭제]");

    int no = Prompt.inputInt("번호? ");

    int index = indexOf(no);

    if (index == -1) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("무료 스터디 삭제를 취소하였습니다.\n");
      return;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.studies[i - 1] = this.studies[i];
    }

    this.studies[--this.size] = null;

    System.out.println();
    System.out.println("무료 스터디를 삭제하였습니다.\n");
  }
}
