package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;

public class MentorRejectHandler extends AbstractMentorApplicantHandler {

  public MentorRejectHandler(List<Member> mentorApplicantList) {
    super(mentorApplicantList);
  }

  @Override
  public void execute() {
  }

  public void execute1(Member member) {
    mentorApplicantList.remove(member);

    System.out.println("멘토 신청을 거절하였습니다.");
  }
}
