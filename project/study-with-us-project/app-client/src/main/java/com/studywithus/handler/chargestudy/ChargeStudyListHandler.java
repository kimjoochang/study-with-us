package com.studywithus.handler.chargestudy;

import java.util.Collection;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;

public class ChargeStudyListHandler implements Command {

  StudyDao chargeStudyDao;

  public ChargeStudyListHandler(StudyDao chargeStudyDao) {
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 조회]\n");

    Collection<Study> chargeStudyList = chargeStudyDao.findAll();

    if (chargeStudyList.isEmpty()) {
      System.out.println("유료 스터디 게시글이 존재하지 않습니다.");
      return;
    }

    for (Study chargeStudy : chargeStudyList) {
      String status = StudyStatusHelper.studyStatus(chargeStudy);
      System.out.printf
      ("[번호 = %d, 제목 = %s, 설명 = %s, 지역 = %s, 가격 = %d, 멘토 = %s, 시작일 = %s, 종료일 = %s,"
          + " 스터디 진행상태 = %s, 등록일 = %s, 모집인원 = %d / %d, 조회수 = %d, 좋아요 = %d]\n",
          chargeStudy.getNo(),
          chargeStudy.getTitle(),
          chargeStudy.getContent(),
          chargeStudy.getArea(),
          chargeStudy.getPrice(),
          chargeStudy.getWriter().getName(),
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
