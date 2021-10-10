package com.studywithus.handler.chargestudy;

import java.util.Collection;
import com.studywithus.dao.ChargeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class RegisterChargeStudyListHandler implements Command {

  ChargeStudyDao chargeStudyDao;

  public RegisterChargeStudyListHandler(ChargeStudyDao chargeStudyDao) {
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 내가 생성한 유료 스터디]\n");


    Collection<Study> chargeStudyList = chargeStudyDao.findAll();

    for (Study chargeStudy : chargeStudyList) {

      if (chargeStudy.getWriter().getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
        System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 모집인원 = %d / %d, 조회수 = %d, 좋아요 = %d]\n",
            chargeStudy.getNo(),
            chargeStudy.getTitle(),
            chargeStudy.getWriter().getName(),
            chargeStudy.getRegisteredDate(),
            chargeStudy.getMembers().size(),
            chargeStudy.getMaxMembers(),
            chargeStudy.getViewCount(),
            chargeStudy.getLikeMembers().size());
      }
    }
    System.out.println();
  }
}
