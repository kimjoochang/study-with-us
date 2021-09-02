package com.studywithus.handler;

import java.sql.Date;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudyListHandler extends AbstractFreeStudyHandler {

  static final int MAX_LENGTH = 5;

  FreeStudy[] studies = new FreeStudy[MAX_LENGTH];
  int size = 0;

  // 무료 스터디 조회
  public void execute() {
    System.out.println("[무료 스터디 조회]");

    for (int i = 0; i < this.size; i++) {
      System.out.printf("%d, %s, %s, %s, %d\n", this.studies[i].getNo(), this.studies[i].getTitle(),
          this.studies[i].getWriter(), this.studies[i].getRegisteredDate(),
          this.studies[i].getViewCount(), this.studies[i].getLike());
    }
  }
}
