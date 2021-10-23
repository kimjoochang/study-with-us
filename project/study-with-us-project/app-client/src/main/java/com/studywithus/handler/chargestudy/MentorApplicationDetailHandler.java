package com.studywithus.handler.chargestudy;

import java.util.Collection;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.MentorApplication;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class MentorApplicationDetailHandler implements Command {

  MentorApplicationDao mentorApplicationDao;

  public MentorApplicationDetailHandler (MentorApplicationDao mentorApplicationDao) {
    this.mentorApplicationDao = mentorApplicationDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[멘토 승인 내역 / 상세보기]\n");

    Collection<MentorApplication> mentorApplicationList = mentorApplicationDao.findAll();

    if (mentorApplicationList.isEmpty()) {
      System.out.println("신청한 멘토가 존재하지 않습니다.");
      return;
    }

    int type = 0;
    // 멘토 신청자 조회
    for (MentorApplication mentorApplication : mentorApplicationList) {
      if (mentorApplication.getStatus() == 0) {
        type = 1;
        System.out.printf("[멘토 신청자 번호 = %d, 멘토 신청자 이메일 = %s, 유료 스터디 주제 = %s, 등록일 = %s]\n",
            mentorApplication.getApplicant().getNo(),
            mentorApplication.getApplicant().getEmail(),
            mentorApplication.getChargeStudySubject(),
            mentorApplication.getRegisteredDate());
      }
    }

    if (type == 0) {
      System.out.println("신청한 멘토가 존재하지 않습니다.");
      return;
    }

    System.out.println();
    int no = Prompt.inputInt("멘토 신청자의 회원번호를 입력하세요. > ");

    System.out.println();

    MentorApplication mentorApplication = mentorApplicationDao.findByNo(no);

    if (mentorApplication == null || mentorApplication.getStatus() != 0) {
      System.out.println("입력하신 이메일과 일치하는 신청 내역이 없습니다.");
      return;
    }

    System.out.printf("신청자 이름: %s\n", mentorApplication.getApplicant().getName());
    System.out.printf("신청자 아이디: %s\n", mentorApplication.getApplicant().getEmail());
    System.out.printf("자기소개: %s\n", mentorApplication.getSelfIntroduction());
    System.out.printf("스터디 주제: %s\n", mentorApplication.getChargeStudySubject());
    System.out.printf("등록일: %s\n", mentorApplication.getRegisteredDate());

    request.setAttribute("applicantNo", no);

    System.out.println();
    System.out.println("1. 승인");
    System.out.println("2. 거절");
    System.out.println("0. 이전\n");

    while (true) {
      int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
      System.out.println();

      if (input == 1) {
        mentorApplication.setStatus(1);
        mentorApplicationDao.update(mentorApplication);
        request.getRequestDispatcher("/mentorApplication/approve").forward(request);
        break;

      } else if (input == 2) {
        mentorApplication.setStatus(2);
        mentorApplication.setRemarks(Prompt.inputString("거절 사유를 입력해주세요. > "));
        mentorApplicationDao.update(mentorApplication);
        System.out.println("멘토 신청을 거절하였습니다.");
        break;

      } else if (input == 0) {
        return;

      } else {
        System.out.println("무효한 메뉴 번호입니다.\n");
        continue;
      }
    }
  }

}
