package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler2.AuthLoginHandler;
import com.studywithus.util.Prompt;

public class StudyDeleteHandler extends AbstractStudyHandler {

  public StudyDeleteHandler(List<Study> freeStudyList, List<Study> chargeStudyList) {
    super(freeStudyList, chargeStudyList);
  }

  @Override
  public void execute() {

    // 무료 스터디 삭제
    if (Study.value) {
      System.out.println("[무료 스터디 / 삭제]");
      int no = Prompt.inputInt("번호? ");

      Study freeStudy = findByNo(no);

      if (freeStudy == null) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }

      if (freeStudy.getWriter() != AuthLoginHandler.getLoginUser()) {
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

    // 유료 스터디 삭제
    else {
      System.out.println("[유료 스터디 / 삭제 요청]");
      int no = Prompt.inputInt("번호? ");

      Study chargeStudy = findByNo(no);

      if (chargeStudy == null) {
        System.out.println();
        System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
        return;
      }

      String input = Prompt.inputString("정말 삭제 요청 하시겠습니까? (y/N) ");
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println();
        System.out.println("유료 스터디 삭제 요청을 취소하였습니다.\n");
        return;
      }

      chargeStudyList.add(chargeStudy);

      System.out.println("삭제 요청이 완료되었습니다.\n");
    }
  }
}
