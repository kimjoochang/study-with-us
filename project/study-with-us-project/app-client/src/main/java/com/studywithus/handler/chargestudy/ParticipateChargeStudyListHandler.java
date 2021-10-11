package com.studywithus.handler.chargestudy;

import java.util.Collection;
import com.studywithus.dao.ChargeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class ParticipateChargeStudyListHandler implements Command {

  ChargeStudyDao chargeStudyDao;

  public ParticipateChargeStudyListHandler(ChargeStudyDao chargeStudyDao) {
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[마이 페이지 / 내가 참여한 유료 스터디]\n");

    Collection<Study> chargeStudyList = chargeStudyDao.findAll();

    int count = 0; 

    for (Study chargeStudy : chargeStudyList) {

      if (chargeStudy.getMenteeEmailList().contains(AuthLogInHandler.getLoginUser().getEmail())){
        System.out.printf(
            "[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 모집인원 = %d / %d, 조회수 = %d, 좋아요 = %d]\n",
            chargeStudy.getNo(), chargeStudy.getTitle(), chargeStudy.getWriter().getName(),
            chargeStudy.getRegisteredDate(), chargeStudy.getMembers().size(),
            chargeStudy.getMaxMembers(), chargeStudy.getViewCount(),
            chargeStudy.getLikeMembers().size());
        count++;
      }
    }

    if (count == 0) {
      System.out.println("내가 참여한 유료 스터디가 존재하지 않습니다.");
      return;
    }
  }
}
