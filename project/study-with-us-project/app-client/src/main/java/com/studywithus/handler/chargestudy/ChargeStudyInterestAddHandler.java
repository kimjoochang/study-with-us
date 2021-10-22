package com.studywithus.handler.chargestudy;

import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ChargeStudyInterestAddHandler implements Command {

  InterestDao interestDao;

  public ChargeStudyInterestAddHandler(InterestDao interestDao) {
    this.interestDao = interestDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기 / 관심 목록 추가]\n");

    int no = (int) request.getAttribute("chargeNo");

    while(true) {
      String input = Prompt.inputString("유료 스터디 관심 목록에 추가하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("유료 스터디 관심 목록 추가를 취소하였습니다.\n");
        return;

      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {

        interestDao.update(chargeStudy);

        System.out.println();
        System.out.println("유료 스터디 관심 목록에 추가되었습니다.\n");
        break;
      }
    }

  }
}
