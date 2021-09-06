package com.studywithus.handler;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudyAddHandler extends AbstractFreeStudyHandler {

  public FreeStudyAddHandler(List<FreeStudy> freeStudyList) {
    super(freeStudyList);
  }

  // 무료 스터디 생성
  @Override
  public void execute() {
    System.out.println("[무료 스터디 / 생성]\n");

    FreeStudy freeStudy = new FreeStudy();

    freeStudy.setWriter(AuthLoginHandler.getLoginUser());

    freeStudy.setNo(Prompt.inputInt("번호? "));

    System.out.println("온/오프라인?");
    System.out.println("1. 온라인");
    System.out.println("2. 오프라인");
    freeStudy.setOnOffLine(Prompt.inputInt("> "));

    if (freeStudy.getOnOffLine() == 2) {
      freeStudy.setArea(Prompt.inputString("지역? "));

    } else {
      freeStudy.setArea(null);
    }

    freeStudy.setTitle(Prompt.inputString("제목? "));
    freeStudy.setExplanation(Prompt.inputString("설명? "));
    freeStudy.setRule(Prompt.inputString("규칙? "));
    freeStudy.setRegisteredDate(new Date(System.currentTimeMillis()));

    freeStudyList.add(freeStudy);

    System.out.println();
    System.out.println("무료 스터디 등록이 완료되었습니다.\n");
  }
}
