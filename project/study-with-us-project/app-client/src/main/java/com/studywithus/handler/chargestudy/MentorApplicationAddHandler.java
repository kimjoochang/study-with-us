package com.studywithus.handler.chargestudy;

import java.sql.Date;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class MentorApplicationAddHandler implements Command {

  MentorApplicationDao mentorApplicationDao;


  public MentorApplicationAddHandler(MentorApplicationDao mentorApplicationDao) {
    this.mentorApplicationDao = mentorApplicationDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 멘토 신청]\n");

    MentorApplicationForm mentorApplication = new MentorApplicationForm();

    if (AuthLogInHandler.loginUser.isMentor()) {
      System.out.println("이미 멘토입니다.");
      return;
    }

    String email = AuthLogInHandler.loginUser.getEmail();
    MentorApplicationForm mentorApplicantEmail = mentorApplicationDao.findByEmail(email);

    // requestAgent.request("mentorApplication.selectOneByEmail", AuthLogInHandler.loginUser.getEmail());

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      System.out.println("이미 멘토 신청이 완료되었습니다.");
      return;
    }

    while (true) {
      String selfIntro = Prompt.inputString("자기 소개를 입력하세요. > ");
      String subject = Prompt.inputString("개설할 스터디 주제를 입력하세요. > ");
      String explanation = Prompt.inputString("스터디 설명을 입력하세요. > ");
      System.out.println();

      if (selfIntro.equals("") || subject.equals("") || explanation.equals("")) {
        System.out.println("모두 필수입력 항목입니다.\n");
        continue;

      } else {
        mentorApplication.setVisible(true);
        mentorApplication.setName(AuthLogInHandler.loginUser.getName());
        mentorApplication.setMentorApplicantEmail(AuthLogInHandler.loginUser.getEmail());
        mentorApplication.setSelfIntroduction(selfIntro);
        mentorApplication.setChargeStudySubject(subject);
        mentorApplication.setChargeStudyExplanation(explanation);
        mentorApplication.setRegisteredDate(new Date(System.currentTimeMillis()));

        requestAgent.request("mentorApplication.insert", mentorApplication);

        System.out.println("멘토 신청이 완료되었습니다.");
      }
      break;
    }
  }
}
