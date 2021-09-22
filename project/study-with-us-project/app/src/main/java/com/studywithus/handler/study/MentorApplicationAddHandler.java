package com.studywithus.handler.study;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class MentorApplicationAddHandler implements Command {

  List<MentorApplicationForm> mentorApplicationFormList;
  List<Member> memberList;

  public MentorApplicationAddHandler(List<MentorApplicationForm> mentorApplicationFormList, List<Member> memberList) {
    this.mentorApplicationFormList = mentorApplicationFormList;
    this.memberList = memberList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[멘토 신청하기]");

    MentorApplicationForm applyMentor = new MentorApplicationForm();
    Member member = AuthLogInHandler.loginUser;

    if (!mentorApplicationFormList.isEmpty()) {
      System.out.println("이미 멘토 신청이 완료되었습니다.");
    }

    else {
      while (true) {
        String selfIntro = Prompt.inputString("자기 소개: ");
        String subject = Prompt.inputString("개설할 스터디 주제: ");
        String explanation = Prompt.inputString("스터디 설명: ");

        if (selfIntro.equals("") || subject.equals("") || explanation.equals("")) {
          System.out.println("모두 필수입력 항목입니다.");
          continue;

        } else {
          applyMentor.setMentorMember(member);
          applyMentor.setSelfIntroduction(selfIntro);
          applyMentor.setChargeStudySubject(subject);
          applyMentor.setChargeStudyExplanation(explanation);
          applyMentor.setRegisteredDate(new Date(System.currentTimeMillis()));

          mentorApplicationFormList.add(applyMentor);

          System.out.println("멘토 신청이 완료되었습니다.");
        }
        break;
      }
    }
  }
}
