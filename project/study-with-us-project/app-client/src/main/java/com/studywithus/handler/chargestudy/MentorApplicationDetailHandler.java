package com.studywithus.handler.chargestudy;

import java.util.Collection;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class MentorApplicationDetailHandler implements Command {

  MentorApplicationDao mentorApplicationDao;

  public MentorApplicationDetailHandler (MentorApplicationDao mentorApplicationDao) {
    this.mentorApplicationDao = mentorApplicationDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[멘토 승인 내역 / 상세보기]\n");

    requestAgent.request("mentorApplication.selectList", null);

    Collection<MentorApplicationForm> mentorApplicationList = requestAgent.getObjects(MentorApplicationForm.class);

    if (requestAgent.getStatus() == RequestAgent.FAIL) {
      System.out.println("신청한 멘토가 존재하지 않습니다.");
      return;
    }

    if (mentorApplicationList.isEmpty()) {
      System.out.println("신청한 멘토가 존재하지 않습니다.");
      return;
    }

    int type = 0;
    // 멘토 신청자 조회
    for (MentorApplicationForm mentorApplication : mentorApplicationList) {
      if (mentorApplication.isVisible()) {
        type = 1;
        System.out.printf("[멘토 신청자 이메일 = %s, 유료 스터디 주제 = %s, 등록일 = %s]\n",
            mentorApplication.getMentorApplicantEmail(),
            mentorApplication.getChargeStudySubject(),
            mentorApplication.getRegisteredDate());
      }
    }

    if (type == 0) {
      System.out.println("신청한 멘토가 존재하지 않습니다.");
      return;
    }

    System.out.println();
    String email = Prompt.inputString("멘토 신청자 이메일을 입력하세요. > ");

    requestAgent.request("mentorApplication.selectOneByEmail", email);

    System.out.println();

    MentorApplicationForm mentorApplication = requestAgent.getObject(MentorApplicationForm.class);

    if (mentorApplication == null || mentorApplication.isVisible() == false) {
      System.out.println("입력하신 이메일과 일치하는 신청 내역이 없습니다.");
      return;
    }

    System.out.printf("신청자 이름: %s\n", mentorApplication.getName());
    System.out.printf("신청자 아이디: %s\n", mentorApplication.getMentorApplicantEmail());
    System.out.printf("자기소개: %s\n", mentorApplication.getSelfIntroduction());
    System.out.printf("스터디 주제: %s\n", mentorApplication.getChargeStudySubject());
    System.out.printf("스터디 설명: %s\n", mentorApplication.getChargeStudyExplanation());
    System.out.printf("등록일: %s\n", mentorApplication.getRegisteredDate());

    request.setAttribute("applicantName", email);

    System.out.println();
    System.out.println("1. 승인");
    System.out.println("2. 거절");
    System.out.println("0. 이전\n");

    while (true) {
      int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
      System.out.println();

      if (input == 1) {
        mentorApplication.setVisible(false);
        requestAgent.request("mentorApplication.update", mentorApplication);
        request.getRequestDispatcher("/mentorApplication/approve").forward(request);
        break;

      } else if (input == 2) {
        requestAgent.request("mentorApplication.delete", email);
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
