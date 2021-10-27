package com.studywithus.servlet.chargestudy;

import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;
import com.studywithus.util.StudyStatusHelper;

public class ChargeStudyDetailHandler implements Command {

  StudyDao chargeStudyDao;
  ChargeStudyDetailMenuPrompt chargeStudyDetailMenuPrompt;

  public ChargeStudyDetailHandler(StudyDao chargeStudyDao, ChargeStudyDetailMenuPrompt chargeStudyDetailMenuPrompt) {
    this.chargeStudyDao = chargeStudyDao;
    this.chargeStudyDetailMenuPrompt = chargeStudyDetailMenuPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");

    Study chargeStudy = chargeStudyDao.findByNo(no);

    if (chargeStudy == null || chargeStudy.getPrice() == 0) {
      System.out.println("해당 번호의 유료 스터디가 없습니다.");
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
    System.out.printf("좋아요수: %d\n", chargeStudy.getLikes());
    System.out.println();

    request.setAttribute("chargeNo", no);

    // 본인이 작성한 글 상세보기 시 경우 보이는 메뉴
    if (chargeStudy.getWriter().getNo() == AuthLogInHandler.getLoginUser().getNo()) {

      chargeStudyDetailMenuPrompt.myStudySelectedMenu(chargeStudy);

      // 타인이 작성한 글 상세보기 시 보이는 메뉴
    } else {
      chargeStudyDetailMenuPrompt.anotherStudySelectedMenu(chargeStudy);

    }
  }

}

