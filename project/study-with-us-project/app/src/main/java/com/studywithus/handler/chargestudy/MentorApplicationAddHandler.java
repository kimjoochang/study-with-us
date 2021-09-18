package com.studywithus.handler;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.util.Prompt;

public class MentorApplicationAddHandler implements Command {

  List<MentorApplicationForm> mentorApplicationForm;

  public MentorApplicationAddHandler (List<MentorApplicationForm> mentorApplicationForm) {
    this.mentorApplicationForm = mentorApplicationForm;
  }

  @Override
  public void execute(CommandRequest request) {
    MentorApplicationForm mentorApplication = new MentorApplicationForm();

    System.out.println("[멘토 신청하기]");

    while (true) {
      String selfIntro = Prompt.inputString("자기 소개: ");
      String subject = Prompt.inputString("개설할 스터디 주제: ");
      String explanation = Prompt.inputString("스터디 설명: ");

      if (selfIntro.equals("") || subject.equals("") || explanation.equals("")) {
        System.out.println("모두 필수입력 항목입니다.");
        continue;

      } else {
        mentorApplication.setName(AuthLogInHandler.loginUser.getName());
        mentorApplication.setId(AuthLogInHandler.loginUser.getId());
        mentorApplication.setSelfIntroduction(selfIntro);
        mentorApplication.setChargeStudySubject(subject);
        mentorApplication.setChargeStudyExplanation(explanation);
        mentorApplication.setRegisteredDate(new Date(System.currentTimeMillis()));

        System.out.println("멘토 신청이 완료되었습니다.");

        mentorApplicationForm.add(mentorApplication);
      }
      break;
    }
  }
}
