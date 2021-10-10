package com.studywithus.handler.chargestudy;

import com.studywithus.dao.ChargeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ChargeStudyDeleteRequestHandler implements Command {

  ChargeStudyDao chargeStudyDao;

  public ChargeStudyDeleteRequestHandler(ChargeStudyDao chargeStudyDao)  {
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 삭제 요청]\n");
    int no = (int) request.getAttribute("chargeNo");

    Study chargeStudy = chargeStudyDao.findByNo(no);

    String input = Prompt.inputString("정말 삭제 요청 하시겠습니까? (y/N) ");
    System.out.println();
    while (true) {
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("유료 스터디 삭제 요청을 취소하였습니다.\n");
        return;
      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("다시 입력하세요.\n");
        continue;
      } else {
        break;
      }
    }
    chargeStudy.setDeleteRequest(true);

    chargeStudyDao.update(chargeStudy);

    System.out.println("삭제 요청이 완료되었습니다.\n");
  }
}
