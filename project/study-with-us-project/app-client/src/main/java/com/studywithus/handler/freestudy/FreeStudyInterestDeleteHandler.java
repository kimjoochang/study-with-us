package com.studywithus.handler.freestudy;

import com.studywithus.dao.StudyDao;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class FreeStudyInterestDeleteHandler implements Command {

  StudyDao freeStudyDao;

  public FreeStudyInterestDeleteHandler(StudyDao freeStudyDao) {
    this.freeStudyDao = freeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 상세보기 / 관심 목록 / 삭제]\n");
    int no = (int) request.getAttribute("freeNo");

    int count = freeStudyDao.findMyInterest(AuthLogInHandler.getLoginUser().getNo(), no);

    if (count == 0) {
      System.out.println("관심목록에 해당 스터디가 없습니다.");
    }

    while (true) {
      String input = Prompt.inputString("무료 스터디 관심 목록을 삭제하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println();
        System.out.println("무료 스터디 관심 목록 삭제를 취소하였습니다.\n");
        return;

      } else if (input.equalsIgnoreCase("y")) {

        freeStudyDao.deleteInterest(AuthLogInHandler.getLoginUser().getNo(), no);
        System.out.println();
        System.out.println("무료 스터디 관심 목록을 삭제하였습니다.");
        return;

      } else {
        System.out.println();
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }
  }
}
