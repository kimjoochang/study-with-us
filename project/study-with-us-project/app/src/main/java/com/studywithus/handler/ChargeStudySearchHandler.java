package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class ChargeStudySearchHandler extends AbstractChargeStudyHandler{

  public ChargeStudySearchHandler(List<Study> chargeStudyList) {
    super(chargeStudyList);	
  }

  // 유료 스터디 검색
  @Override
  public void execute() {
    System.out.println("[유료스터디 / 검색]");

    String input = Prompt.inputString("검색어? ");
    System.out.println();

    for (Study study : chargeStudyList) {
      if (!study.getTitle().contains(input) &&
          !study.getExplanation().contains(input) &&
          !study.getWriter().contains(input)) {
        System.out.println("입력하신 검색어가 포함된 게시물이 없습니다.");
        continue;
      }
      System.out.printf("%d, %s, %s, %s, %d, %d\n", 
          study.getNo(), 
          study.getTitle(), 
          study.getWriter(),
          study.getRegisteredDate(),
          study.getViewCount(), 
          study.getLike());
    }
  }
}
