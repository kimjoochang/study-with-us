package com.studywithus.handler.freestudy;

import java.util.Collection;
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.StudyStatusHelper;

public class ParticipateFreeStudyListHandler implements Command {

  StudyMemberDao studyMemberDao;

  public ParticipateFreeStudyListHandler(StudyMemberDao studyMemberDao) {
    this.studyMemberDao = studyMemberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 내가 참여한 무료 스터디 / 조회]\n");

    Collection<Study> freeStudyList = studyMemberDao.findAllStudy(AuthLogInHandler.getLoginUser().getNo(), Study.PARTICIPANT_STATUS);

    if (freeStudyList.isEmpty()) {
      System.out.println("무료 스터디 게시글이 존재하지 않습니다.");
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
