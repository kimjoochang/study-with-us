package com.studywithus.handler.freestudy;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.abstract.AbstractStudyHandler;
import com.studywithus.util.Prompt;

public class FreeStudySearchHandler extends AbstractStudyHandler {

  public FreeStudySearchHandler(List<Study> freeStudyList) {
    super(freeStudyList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 / 검색]");

    String input = Prompt.inputString("검색할 키워드를 입력하세요. > ");

    for (Study freeStudy : studyList) {
      if (!freeStudy.getTitle().contains(input) &&
          !freeStudy.getContent().contains(input) &&
          !freeStudy.getWriter().getName().contains(input)) {

        System.out.println("입력하신 키워드가 포함된 게시글이 없습니다.");
        continue;
      }

      System.out.printf("%d, %s, %s, %s, %d, %d\n", 
          freeStudy.getNo(), 
          freeStudy.getTitle(), 
          freeStudy.getWriter().getName(),
          freeStudy.getRegisteredDate(),
          freeStudy.getViewCount(), 
          freeStudy.getLike());
    }
  }
}
