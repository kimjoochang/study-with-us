package com.studywithus.handler.chargestudy;

import java.util.Collection;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.StudyStatusHelper;

public class ParticipateChargeStudyListHandler implements Command {

  StudyDao chargeStudyDao;

  public ParticipateChargeStudyListHandler(StudyDao chargeStudyDao) {
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 내가 참여한 유료 스터디]\n");

    Collection<Study> chargeStudyList = 
        chargeStudyDao.findAllParticipateStudy(AuthLogInHandler.getLoginUser().getNo());

    if (chargeStudyList.isEmpty()) {
      System.out.println("내가 참여한 유료 스터디가 없습니다.");
      return;
    }

    for (Study chargeStudy : chargeStudyList) {
      if (chargeStudy.getPrice() > 0) {
        String status = StudyStatusHelper.studyStatus(chargeStudy);
        System.out.printf
        ("[번호 = %d, 제목 = %s, 설명 = %s, 지역 = %s, 가격 = %d, 멘토 = %s, 시작일 = %s,종료일 = %s,"
            + " 스터디 진행상태 = %s, 등록일 = %s, 모집인원 = %d / %d, 조회수 = %d, 좋아요 = %d]\n",
            chargeStudy.getNo(),
            chargeStudy.getTitle(),
            chargeStudy.getWriter().getName(),
            chargeStudy.getContent(),
            chargeStudy.getArea(),
            chargeStudy.getPrice(),
            chargeStudy.getStartDate(),
            chargeStudy.getEndDate(),
            status,
            chargeStudy.getRegisteredDate(),
            chargeStudy.getMembers().size(),
            chargeStudy.getMaxMembers(),
            chargeStudy.getViewCount(),
            chargeStudy.getLikeMembers().size());
        System.out.println();
      }
    }
  }
}
