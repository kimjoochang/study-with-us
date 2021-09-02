package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.domain.LoginMemberInfo;
import com.studywithus.util.Prompt;

public class FreeStudyApplyHandler extends AbstractFreeStudyHandler {

  public FreeStudyApplyHandler(List<FreeStudy> freeStudyList) {
    super(freeStudyList);
  }

  // 무료 스터디 신청
  public void execute() {
    System.out.println("[무료 스터디 / 신청]");

    String input = Prompt.inputString("무료 스터디를 신청 하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 신청이 취소되었습니다.");
      return;
    }

    LoginMemberInfo.getName();
    LoginMemberInfo.getId();

    System.out.println();
    System.out.println("무료 스터디 신청이 완료되었습니다.");
  }
}
