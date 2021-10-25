package com.studywithus.handler.freestudy;

import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;
import com.studywithus.util.StudyStatusHelper;

public class ParticipateFreeStudyDetailHandler implements Command {

  StudyDao freeStudyDao;

  public ParticipateFreeStudyDetailHandler(StudyDao freeStudyDao) {
    this.freeStudyDao = freeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 내가 참여한 무료 스터디 / 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");

    Study freeStudy = freeStudyDao.
        findByNoParticipateStudy( AuthLogInHandler.getLoginUser().getNo( ), no, 1);

    if (freeStudy == null || freeStudy.getPrice() > 0) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    String status = StudyStatusHelper.studyStatus(freeStudy);

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
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
    System.out.printf("시작일: %s\n", freeStudy.getStartDate());
    System.out.printf("종료일: %s\n", freeStudy.getEndDate());
    System.out.printf("모집인원: %d / %d\n", freeStudy.getMembers().size(), freeStudy.getMaxMembers());
    System.out.printf("설명: %s\n", freeStudy.getContent());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());
    System.out.printf("좋아요: %d\n", freeStudy.getLikeMembers().size());
    System.out.println();

    request.setAttribute("freeNo", no);

    while (true) {
      System.out.println("1. 참여 취소");
      System.out.println("0. 이전");
      System.out.println();

      int num = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
      System.out.println();

      if (num == 1) {
        request.getRequestDispatcher("/freeStudy/participationCancel").forward(request);
        return;

      } else if (num == 0) {
        return;

      } else {
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }
  }
}
// }
