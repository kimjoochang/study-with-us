package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class FreeStudyDeleteHandler extends AbstractStudyHandler {

  public FreeStudyDeleteHandler(List<Study> freeStudyList) {
    super(freeStudyList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 / 삭제]");
    int no = (int) request.getAttribute("freeNo");

    Study freeStudy = findByNo(no);

    if (freeStudy == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    if (freeStudy.getWriter().getId() != AuthLogInHandler.getLoginUser().getId()) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");
    System.out.println();
    while(true) {
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("게시글 삭제를 취소하였습니다.");
        return;

      }else if (!input.equalsIgnoreCase("y")) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    studyList.remove(freeStudy);

    System.out.println("게시글을 삭제하였습니다.");
  }
}
