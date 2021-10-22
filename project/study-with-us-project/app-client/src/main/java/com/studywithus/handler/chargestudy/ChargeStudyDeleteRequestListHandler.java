package com.studywithus.handler.chargestudy;

import java.util.Collection;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;

public class ChargeStudyDeleteRequestListHandler implements Command {

  StudyDao chargeStudyDao;

  public ChargeStudyDeleteRequestListHandler(StudyDao chargeStudyDao) {
    this.chargeStudyDao = chargeStudyDao;
  }

  // 관리자 관점
  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[스터디 삭제 요청 내역 / 조회]\n");

    Collection<Study> studyList = chargeStudyDao.findAll();

    for(Study chargeStudy : studyList) {
      if (chargeStudy.getDeleteStatus() == 1) {
        System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 모집인원 = %d / %d]\n",
            chargeStudy.getNo(),
            chargeStudy.getTitle(),
            chargeStudy.getWriter().getName(),
            chargeStudy.getRegisteredDate(),
            chargeStudy.getMembers().size(),
            chargeStudy.getMaxMembers());
      }
    }
  }
}
