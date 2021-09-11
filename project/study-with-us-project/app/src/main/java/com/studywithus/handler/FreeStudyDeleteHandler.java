package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudyDeleteHandler extends AbstractFreeStudyHandler {

  public FreeStudyDeleteHandler(List<FreeStudy> freeStudyList) {
    super(freeStudyList);
  }

  // 무료 스터디 삭제
  @Override
  public void execute() {
    System.out.println("[무료 스터디 / 삭제]");
    int no = Prompt.inputInt("번호? ");

    FreeStudy freeStudy = findByNo(no);

    if (freeStudy == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    if (freeStudy.getWriter().getNo() != AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 삭제를 취소하였습니다.");
      return;
    }

    freeStudyList.remove(freeStudy);

    System.out.println("게시글을 삭제하였습니다.");
  }
}
