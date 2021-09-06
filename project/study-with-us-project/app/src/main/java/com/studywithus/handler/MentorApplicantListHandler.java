package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;

public class MentorApplicantListHandler extends AbstractMentorApplicantHandler {

  public MentorApplicantListHandler (List<Member> mentorApplicantList) {
    super(mentorApplicantList);
  }
  @Override
  public void execute() {
    System.out.println("[멘토 신청 내역 / 조회]\n");

    for(Member mentorApplicant : mentorApplicantList) {
      System.out.println("신청자 이름 : " + mentorApplicant.getName());
    }
  }
}


