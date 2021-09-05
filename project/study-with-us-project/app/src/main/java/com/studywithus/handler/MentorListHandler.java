package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;

public class MentorListHandler extends AbstractMentorApplicantHandler {

  public MentorListHandler (List<Member> mentorList) {
    super(mentorList);
  }
  @Override
  public void execute() {
    System.out.println("[멘토 신청 내역]\n");

    for(Member mentorApplicant : mentorList) {
      System.out.printf("%s, %s\n",mentorApplicant.getName(), mentorApplicant.getId());
    }
  }
}


