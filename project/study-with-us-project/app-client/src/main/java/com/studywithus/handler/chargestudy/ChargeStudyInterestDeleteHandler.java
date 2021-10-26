package com.studywithus.handler.chargestudy;

import com.studywithus.dao.StudyDao;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;


public class ChargeStudyInterestDeleteHandler implements Command {

  StudyDao chargeStudyDao;

  public ChargeStudyInterestDeleteHandler(StudyDao chargeStudyDao) {
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이페이지 / 유료 스터디 / 관심 목록 / 삭제]\n");

    if (request.getAttribute("chargeNo") == null) {

      int no = Prompt.inputInt("삭제할 관심목록 번호를 입력하세요. > ");

      if (chargeStudyDao.findMyInterest(AuthLogInHandler.getLoginUser().getNo(), no) == 0) {
        System.out.println("해당 번호의 관심목록이 없습니다.\n");
        return;
      }

      while (true) {
        String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

        if (input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("유료 스터디 관심 목록 삭제를 취소하였습니다.\n");
          return;

        } else if (!input.equalsIgnoreCase("y")) {
          System.out.println("다시 입력하세요.\n");
          continue;

        } else {
          break;
        }
      }

      chargeStudyDao.deleteInterest(AuthLogInHandler.getLoginUser().getNo(), no);

      System.out.println();
      System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n");

    } else {

      int no = (int) request.getAttribute("chargeNo");

      if (chargeStudyDao.findMyInterest(AuthLogInHandler.getLoginUser().getNo(), no) == 0) {
        System.out.println("유료 스터디 관심목록이 존재하지 않습니다.\n");
        return;
      }

      while (true) {
        String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

        if (input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("유료 스터디 관심 목록을 취소하였습니다.\n");
          return;

        } else if (!input.equalsIgnoreCase("y")) {
          System.out.println("다시 입력하세오.\n");
          continue;

        } else {
          break;
        }
      }

      chargeStudyDao.deleteInterest(AuthLogInHandler.getLoginUser().getNo(), no);

      System.out.println();
      System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n");
    }
  }
}
