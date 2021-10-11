package com.studywithus.handler.chargestudy;

import java.util.Collection;
import com.studywithus.dao.ChargeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;

public class ChargeStudyListHandler implements Command {

  ChargeStudyDao chargeStudyDao;

  public ChargeStudyListHandler(ChargeStudyDao chargeStudyDao) {
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 조회]\n");

    Collection<Study> studyList = chargeStudyDao.findAll();

    if (studyList == null) {
      System.out.println("유료 스터디 게시글이 존재하지 않습니다.");
      return;
    }

    for (Study chargeStudy : studyList) {
      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 모집인원 = %d / %d, 조회수 = %d, 좋아요 = %d]\n",
          chargeStudy.getNo(),
          chargeStudy.getTitle(),
          chargeStudy.getWriter().getName(),
          chargeStudy.getRegisteredDate(),
          chargeStudy.getMembers().size(),
          chargeStudy.getMaxMembers(),
          chargeStudy.getViewCount(),
          chargeStudy.getLikeMembersEmail().size());
      System.out.println();
    }
    System.out.println();
  }
}
