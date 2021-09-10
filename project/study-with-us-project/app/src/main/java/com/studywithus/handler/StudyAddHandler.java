package com.studywithus.handler;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler2.AuthLoginHandler;
import com.studywithus.util.Prompt;

public class StudyAddHandler extends AbstractStudyHandler {

  public StudyAddHandler(List<Study> freeStudyList, List<Study> chargeStudyList) {
    super(freeStudyList, chargeStudyList);
  }

  // 무료 스터디 생성
  @Override
  public void execute() {

    if (Study.value) {
      System.out.println("[무료 스터디 / 생성]\n");

      Study freeStudy = new Study();

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

    else {
      // 유료 스터디 생성
      System.out.println("[유료 스터디 / 생성]\n");

      Study chargeStudy = new Study();

      chargeStudy.setNo(Prompt.inputInt("번호? "));
      chargeStudy.setWriter(AuthLoginHandler.getLoginUser());
      chargeStudy.setArea(Prompt.inputString("지역? "));
      chargeStudy.setTitle(Prompt.inputString("스터디 제목? "));
      chargeStudy.setExplanation(Prompt.inputString("스터디 설명? "));
      chargeStudy.setPrice(Prompt.inputInt("가격? " ));
      chargeStudy.setRegisteredDate(new Date(System.currentTimeMillis()));

      chargeStudyList.add(chargeStudy);

      System.out.println();
      System.out.println("유료 스터디 등록이 완료되었습니다.\n");
    }
  }
}
