package com.studywithus.handler.freestudy;

import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class FreeStudyInterestDeleteHandler implements Command {

  FreeStudyDao freeStudyDao;

  public FreeStudyInterestDeleteHandler(FreeStudyDao freeStudyDao) {
    this.freeStudyDao = freeStudyDao;
    // super(freeStudyList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 상세보기 / 관심 목록 / 삭제]\n");
    int no = (int) request.getAttribute("freeNo");

    Study freeStudy = freeStudyDao.findByNo(no);

    while (true) {
      String input = Prompt.inputString("무료 스터디 관심 목록을 삭제하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println();
        System.out.println("무료 스터디 관심 목록 삭제를 취소하였습니다.\n");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        for (int i = 0; i < freeStudy.getLikeMembers().size(); i++) {
          if (freeStudy.getLikeMembers().get(i).getNo() == AuthLogInHandler.getLoginUser().getNo()) {
            freeStudy.getLikeMembers().remove(i);
            break;
          }
        }

        freeStudyDao.update(freeStudy);
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
