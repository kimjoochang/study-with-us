package com.studywithus.handler.chargestudy;

import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;
import com.studywithus.util.StudyStatusHelper;

public class ParticipateChargeStudyDetailHandler implements Command {

  StudyMemberDao studyMemberDao;

  public ParticipateChargeStudyDetailHandler(StudyMemberDao studyMemberDao) {
    this.studyMemberDao = studyMemberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 내가 참여한 유료 스터디 / 상세보기]\n");

    int no = Prompt.inputInt("번호를 입력하세요. > ");


    Study chargeStudy = studyMemberDao.
        findByNoStudy( AuthLogInHandler.getLoginUser().getNo( ), no, Study.PARTICIPANT_STATUS);

    if (chargeStudy == null || chargeStudy.getPrice() == 0) {
      System.out.println("해당 번호의 내가 참여한 유료 스터디가 없습니다.");
      return;
    }

    chargeStudy.setViewCount(chargeStudy.getViewCount() + 1);

    System.out.printf("제목: %s\n", chargeStudy.getTitle());
    System.out.printf("멘토: %s\n", chargeStudy.getWriter().getName());
    System.out.printf("설명: %s\n", chargeStudy.getContent());
    System.out.printf("지역: %s\n", chargeStudy.getArea());
    System.out.printf("가격: %s\n", chargeStudy.getPrice());
    System.out.printf("시작일: %s\n", chargeStudy.getStartDate());
    System.out.printf("종료일: %s\n", chargeStudy.getEndDate());
    System.out.printf("스터디 진행상태: %s\n", StudyStatusHelper.studyStatus(chargeStudy));
    System.out.printf("등록일: %s\n", chargeStudy.getRegisteredDate());

    System.out.printf("모집인원 = %d / %d\n", chargeStudy.getMembers(), chargeStudy.getMaxMembers());
    System.out.printf("조회수: %d\n", chargeStudy.getViewCount());
    System.out.printf("좋아요 수: %d\n", chargeStudy.getLikes());
    System.out.println();

    request.setAttribute("chargeNo", no);

    System.out.println("1. 결제 취소"); // 모집중, 진행중에서만 출력되게 (진행완료는 후기로 출력)
    System.out.println("0. 이전\n");

    int input = Prompt.inputInt("메뉴 번호를 선택하세요. > "); 
    System.out.println();

    if (input == 1) {
      request.getRequestDispatcher("/chargeStudy/paymentCancel").forward(request);
    } else if (input == 0) {
      return;
    }
  }
}
