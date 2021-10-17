package com.studywithus.handler.freestudy;

import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class FreeStudyParticipationCancelHandler implements Command {

  FreeStudyDao freeStudyDao;

  public FreeStudyParticipationCancelHandler(FreeStudyDao freeStudyDao) {
    this.freeStudyDao = freeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 상세보기 / 참여 취소]\n");
    int no = (int) request.getAttribute("freeNo");

    Study freeStudy = freeStudyDao.findByNo(no);

    if (freeStudy == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    while (true) {
      String input = Prompt.inputString("무료 스터디 참여를 취소하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println();
        System.out.println("무료 스터디 참여 취소가 취소되었습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        for (int i = 0; i < freeStudy.getParticipants().size(); i++) {
          if (freeStudy.getParticipants().get(i).getNo() == AuthLogInHandler.getLoginUser()
              .getNo()) {
            freeStudy.getParticipants().remove(i);
            break;
          }
        }

        freeStudyDao.update(freeStudy);
        System.out.println();
        System.out.println("무료 스터디 참여를 취소하였습니다.");
        return;

      } else {
        System.out.println();
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }
  }
}
