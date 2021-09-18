package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ChargeStudySearchHandler extends AbstractStudyHandler {

  public ChargeStudySearchHandler(List<Study> chargeStudyList) {
    super(chargeStudyList);	
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[유료스터디 / 검색]");

    String input = Prompt.inputString("검색할 키워드를 입력하세요. > ");
    System.out.println();

    for (Study chargeStudy : studyList) {
      if (!chargeStudy.getTitle().contains(input) &&
          !chargeStudy.getContent().contains(input) &&
          !chargeStudy.getWriter().getName().contains(input)) {

        System.out.println("입력하신 키워드가 포함된 게시글이 없습니다.");
        continue;
      }

      System.out.printf("%d, %s, %s, %s, %d, %d\n", 
          chargeStudy.getNo(), 
          chargeStudy.getTitle(), 
          chargeStudy.getWriter(),
          chargeStudy.getRegisteredDate(),
          chargeStudy.getViewCount(), 
          chargeStudy.getLike());
    }
  }
}
