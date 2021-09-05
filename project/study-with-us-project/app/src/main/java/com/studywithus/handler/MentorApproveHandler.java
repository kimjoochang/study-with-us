package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Mentor;

public class MentorApproveHandler extends AbstractMentorHandler {

  Mentor mentor = new Mentor();

  public MentorApproveHandler(List<Mentor> mentorList) {
    super(mentorList);
  }

  @Override
  public void execute() {
  }
  public void execute1(Member mentorApplicant) {

    mentor.setId(mentorApplicant.getId());
    mentor.setName(mentorApplicant.getName());

    mentorList.add(mentor);

    System.out.println("멘토 승인이 완료되었습니다.");
  }

}
