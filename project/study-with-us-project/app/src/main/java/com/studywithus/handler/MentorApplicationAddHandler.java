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

  // 멘토 신청하기
  @Override
  public void execute() {
    MentorApplicationForm mentorApplication = new MentorApplicationForm();

    System.out.println("[멘토 신청하기]");

    while (true) {
      String selfIntro = Prompt.inputString("간단한 자기 소개: ");
      String subject = Prompt.inputString("개설할 스터디 주제: ");
      String explanation = Prompt.inputString("간단한 스터디 설명: ");

      if (selfIntro.equals("") || subject.equals("") || explanation.equals("")) {
        System.out.println("모두 필수입력 항목입니다.");
        continue;

      } else {
        mentorApplication.setName(AuthLoginHandler.loginUser.getName());
        mentorApplication.setSelfIntroduction(selfIntro);
        mentorApplication.setChargeStudySubject(subject);
        mentorApplication.setChargeStudyExplanation(explanation);
        mentorApplication.setRegisteredDate(new Date(System.currentTimeMillis()));
        mentorApplication.setMentorApplicantInfo(AuthLoginHandler.getLoginUser());

        System.out.println("멘토 신청이 완료되었습니다.");

        mentorApplicationForm.add(mentorApplication);
      }
      break;
    }
  }
}
