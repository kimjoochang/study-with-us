package com.studywithus.handler;

import java.sql.Date;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudyAddHandler extends AbstractFreeStudyHandler {

  static final int MAX_LENGTH = 5;

  FreeStudy[] studies = new FreeStudy[MAX_LENGTH];
  int size = 0;

  // 무료 스터디 생성
  public void execute() {
    System.out.println("[새 무료 스터디]");

    FreeStudy study = new FreeStudy();

    study.setNo(Prompt.inputInt("번호? "));
    study.setWriter(Prompt.inputString("팀장? "));

    System.out.println("온/오프라인?");
    System.out.println("1. 온라인");
    System.out.println("2. 오프라인");
    study.setOnOffLine(Prompt.inputString("> "));

    if (study.getOnOffLine().equals("2")) {
      study.setArea(Prompt.inputString("지역? "));
    }

    study.setTitle(Prompt.inputString("제목? "));
    study.setExplanation(Prompt.inputString("설명? "));
    study.setRule(Prompt.inputString("규칙? "));
    study.setRegisteredDate(new Date(System.currentTimeMillis()));

    this.studies[this.size++] = study;
    System.out.println();
    System.out.println("무료 스터디 등록이 완료되었습니다.\n");
  }
}
