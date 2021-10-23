package com.studywithus.handler.chargestudy;

import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyAddHandler implements Command {

  StudyDao chargeStudyDao;

  public ChargeStudyAddHandler(StudyDao chargeStudyDao) {
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 생성]\n");

    Study chargeStudy = new Study();

    chargeStudy.setWriter(AuthLogInHandler.getLoginUser());
    chargeStudy.setArea(Prompt.inputString("지역: "));
    chargeStudy.setTitle(Prompt.inputString("스터디 제목: "));
    chargeStudy.setContent(Prompt.inputString("스터디 설명: "));
    chargeStudy.setMaxMembers(Prompt.inputInt("모집 인원: "));
    chargeStudy.setPrice(Prompt.inputInt("가격: " ));

    while (true) {
      try {
        chargeStudy.setStartDate(Prompt.inputDate("시작일을 입력하세요. ex) YYYY-MM-DD > "));

      } catch (IllegalArgumentException e) {
        System.out.println("다시 입력하세요.\n");
        continue;
      }

      break;
    }

    while (true) {
      try {
        chargeStudy.setEndDate(Prompt.inputDate("종료일을 입력하세요. ex) YYYY-MM-DD > "));

      } catch (IllegalArgumentException e) {
        System.out.println("다시 입력하세요.\n");
        continue;
      }

      break;
    }

    chargeStudyDao.insert(chargeStudy);

    System.out.println();
    System.out.println("유료스터디 등록이 완료되었습니다.\n");
  }

}

