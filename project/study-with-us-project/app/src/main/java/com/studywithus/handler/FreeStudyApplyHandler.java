package com.studywithus.handler;

import java.sql.Date;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudyApplyHandler extends AbstractFreeStudyHandler {

  static final int MAX_LENGTH = 5;

  FreeStudy[] studies = new FreeStudy[MAX_LENGTH];
  int size = 0;

  // 무료 스터디 신청
  public void execute() {
    System.out.println("[무료 스터디 신청]");
  }
}
