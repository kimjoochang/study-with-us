package com.studywithus.handler.freestudy;

import java.util.List;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.StudyStatusHelper;

public class FreeStudyInterestListHandler implements Command {

  StudyDao freeStudyDao;

  public FreeStudyInterestListHandler(StudyDao freeStudyDao) {
    this.freeStudyDao = freeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이페이지 / 나의 관심 목록 / 무료 스터디 관심목록 / 조회]\n");

    List<Study> freeStudyList = freeStudyDao.findAllInterest(AuthLogInHandler.getLoginUser().getNo());

    if (freeStudyList.isEmpty()) {
      System.out.println("무료 스터디 관심목록이 존재하지 않습니다.");
      return;
    }

    for (Study freeStudy : freeStudyList) {
      if (freeStudy.getPrice() == 0) {
        String status = StudyStatusHelper.studyStatus(freeStudy);

        System.out.printf("번호: %d\n",freeStudy.getNo());
        System.out.printf("제목: %s\n", freeStudy.getTitle());
        System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());
        System.out.printf("스터디 진행상태 = %s\n", status);

        if (freeStudy.getOnOffLine() == 1) {
          System.out.printf("온/오프라인: 오프라인");
          System.out.printf("지역: %s\n", freeStudy.getArea());

        } else {
          System.out.printf("온/오프라인: 온라인");
        }
        System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

        freeStudy.setViewCount(freeStudy.getViewCount() + 1);
        System.out.printf("조회수: %d\n", freeStudy.getViewCount());
        System.out.printf("좋아요: %d\n", freeStudy.getLikes());
        System.out.println();
      }

    }
  }
}

